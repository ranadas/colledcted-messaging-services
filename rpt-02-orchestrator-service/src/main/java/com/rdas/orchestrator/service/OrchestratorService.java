package com.rdas.orchestrator.service;

import com.rdas.common.message.model.ControlMessage;

public interface OrchestratorService {

    void processStepControlZero(ControlMessage controlMessage);
}
