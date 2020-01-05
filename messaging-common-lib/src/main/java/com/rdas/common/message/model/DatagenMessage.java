package com.rdas.common.message.model;

import lombok.Getter;

public class DatagenMessage extends ReportMessage {
    @Getter
    private final String name;

    public DatagenMessage(String uuid, String name) {
        super(uuid);
        this.name = name;
    }
}
