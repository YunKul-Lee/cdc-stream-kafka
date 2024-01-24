package com.jake.cdc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jake.cdc.config.jackson.MillisToLocalDateTimeDeserializer;
import com.jake.cdc.config.jackson.OperationTypeDeserializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class DebeziumEventDto {

    private Payload payload;

    @Data
    public static class Payload {
        private Source source;
        private Map<String, Object> before;
        private Map<String, Object> after;

        @JsonDeserialize(using = OperationTypeDeserializer.class)
        @JsonProperty("op")
        @JsonFormat
        private OperationType operationType;

        @Data
        public static class Source {
            @JsonProperty("ts_ms")
            @JsonDeserialize(using = MillisToLocalDateTimeDeserializer.class)
            private LocalDateTime transactionTime;

            private String db;
            private String schema;
            private String table;

            @JsonProperty("txId")
            private long transactionId;
        }
    }
}
