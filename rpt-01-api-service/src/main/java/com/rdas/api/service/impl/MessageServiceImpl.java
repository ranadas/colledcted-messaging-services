package com.rdas.api.service.impl;

import com.rdas.api.service.MessageService;
import com.rdas.common.message.model.ControlMessage;
import com.rdas.common.message.observables.MessagePublisher;
import com.rdas.common.message.observables.impl.ControlMessagePublisher;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessagePublisher controlMessagePublisher;

    public MessageServiceImpl(ControlMessagePublisher controlMessagePublisher) {
        this.controlMessagePublisher = controlMessagePublisher;
    }

    @Override
    public String startProcess(ControlMessage controlMessage) {
        //TODO
        controlMessagePublisher.publish(controlMessage);
        return controlMessage.getUuid();
    }
}
