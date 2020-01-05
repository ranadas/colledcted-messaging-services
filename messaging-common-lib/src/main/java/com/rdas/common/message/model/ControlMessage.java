package com.rdas.common.message.model;

import lombok.Getter;

public class ControlMessage extends ReportMessage {
    @Getter
    private final StepDefinitionMark stepDefinitionMark;

    public ControlMessage(String uuid, StepDefinitionMark step) {
        super(uuid);
        this.stepDefinitionMark = step;
    }
}
