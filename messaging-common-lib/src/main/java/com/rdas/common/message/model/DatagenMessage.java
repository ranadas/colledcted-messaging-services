package com.rdas.common.message.model;

import lombok.Getter;

public class DatagenMessage extends ReportMessage {
    @Getter
    private final String name;
    @Getter
    private final StepDefinitionMark stepDefinitionMark;

    public DatagenMessage(String uuid, String name, StepDefinitionMark stepDefinitionMark) {
        super(uuid);
        this.name = name;
        this.stepDefinitionMark = stepDefinitionMark;
    }
}
