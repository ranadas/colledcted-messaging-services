package com.rdas.common.message.model;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

public abstract class ReportMessage implements Serializable {
    @Getter
    private final String uuid;

    protected ReportMessage(String uuid) {
        this.uuid = uuid;
    }
}
