package com.jake.cdc.dto;

import lombok.Getter;

@Getter
public enum OperationType {
    CREATE("c"),
    UPDATE("u"),
    DELETE("d"),
    READ("r");

    private final String code;

    OperationType(String code) {
        this.code = code;
    }

    public static OperationType fromCode(String code) {
        for(OperationType type : OperationType.values()) {
            if(type.getCode().equals(code)) {
                return type;
            }
        }

        throw new IllegalArgumentException("Unknown operation type code: " + code);
    }
}
