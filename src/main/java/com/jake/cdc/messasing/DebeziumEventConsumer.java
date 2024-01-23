package com.jake.cdc.messasing;

import com.jake.cdc.dto.DebeziumEventDto;
import com.jake.cdc.entity.Audit;
import com.jake.cdc.repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class DebeziumEventConsumer implements Consumer<DebeziumEventDto> {

    private final AuditRepository auditRepo;

    @Override
    public void accept(DebeziumEventDto eventDto) {
        try {
            final var payload = eventDto.getPayload();
            final var source = payload.getSource();
            final var audit = Audit.builder()
                    .tableName(source.getTable())
                    .operation(payload.getOperationType().name())
                    .time(source.getTransactionTime())
                    .value(Optional.ofNullable(payload.getAfter()).map(Object::toString).orElse(null))
                    .build();
            final Audit savedAudit = auditRepo.save(audit);
            log.info("{} event on {} table is audited as {}",
                    payload.getOperationType(),
                    source.getTable(),
                    savedAudit);
        } catch (Exception e) {
            log.error("Failed to processing consumed message {}", eventDto, e);
        }
    }
}
