package com.jake.cdc.config.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.jake.cdc.dto.OperationType;

import java.io.IOException;

public class OperationTypeDeserializer extends JsonDeserializer<OperationType> {

    @Override
    public OperationType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return OperationType.fromCode(jsonParser.getValueAsString());
    }
}
