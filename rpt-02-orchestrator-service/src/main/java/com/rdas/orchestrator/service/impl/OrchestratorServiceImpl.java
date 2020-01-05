package com.rdas.orchestrator.service.impl;

import com.rdas.common.message.model.ControlMessage;
import com.rdas.common.message.model.DatagenMessage;
import com.rdas.common.message.model.StepDefinitionMark;
import com.rdas.common.message.observables.MessagePublisher;
import com.rdas.orchestrator.service.OrchestratorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class OrchestratorServiceImpl implements OrchestratorService {

    private final MessagePublisher<DatagenMessage> datagenMessageMessagePublisher;

    public OrchestratorServiceImpl(MessagePublisher<DatagenMessage> datagenMessageMessagePublisher) {
        this.datagenMessageMessagePublisher = datagenMessageMessagePublisher;
    }

    @Override
    public void processStepControlZero(ControlMessage controlMessage) {
        log.info(" Starting to process report Gen, step 0...");

        switch (controlMessage.getStepDefinitionMark()) {
            case ACCEPTED:
                // 1. get database, get steps etc. all init steps work
                // TODO -- db stuff
                // send a message to report generator service
                DatagenMessage datagenMessage = new DatagenMessage(controlMessage.getUuid(), " Starting step 2", StepDefinitionMark.DATAGEN_START);
                datagenMessageMessagePublisher.publish(datagenMessage);
                break;
            case DATAGEN_END:
                // create a Report Gen Messge and push to Q
                break;
            default:
                log.info("\n\n\n Invalid Message ControlMessage, this shouldn't really happen.");
        }

    }
}
