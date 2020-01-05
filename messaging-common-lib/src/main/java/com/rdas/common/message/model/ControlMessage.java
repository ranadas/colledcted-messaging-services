package com.rdas.common.message.model;

import lombok.Getter;

public class ControlMessage extends ReportMessage {
    @Getter
    private final String name;

    public ControlMessage(String uuid, String name) {
        super(uuid);
        this.name = name;
    }
}
