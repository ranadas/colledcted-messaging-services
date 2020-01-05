package com.rdas.orchestrator.service.impl;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.ItemEvent;
import com.hazelcast.core.ItemListener;
import com.rdas.common.message.model.ControlMessage;
import com.rdas.common.message.model.DatagridConfigProperties;
import com.rdas.common.message.model.ReportMessage;
import com.rdas.common.message.observables.MessageSubscriber;
import com.rdas.orchestrator.service.OrchestratorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public final class ControlMessageSubscriber<T extends ReportMessage> implements MessageSubscriber<ControlMessage> {
    private IQueue<ControlMessage> controlMessageQueue;
    private final DatagridConfigProperties datagridConfigProperties;

    private final OrchestratorService orchestratorService;

    public ControlMessageSubscriber(HazelcastInstance hazelcastInstance,
                                    DatagridConfigProperties datagridConfigProperties,
                                    OrchestratorService orchestratorService) {
        this.datagridConfigProperties = datagridConfigProperties;
        controlMessageQueue = hazelcastInstance.getQueue(datagridConfigProperties.getControlQueueName());
        this.orchestratorService = orchestratorService;


        controlMessageQueue.addItemListener(
                new ItemListener<>() {
                    @Override
                    public void itemAdded(ItemEvent<ControlMessage> item) {
                        log.info("\n ** controlMessageQueue ControlMessage Added: {}", item);
                        orchestratorService.processStepControlZero(item.getItem());
                    }

                    @Override
                    public void itemRemoved(ItemEvent<ControlMessage> item) {
                        log.info("controlMessageQueue ControlMessage Removed: {}", item);
                    }
                }, true);
        log.info("\n ****** Starting ControlMessage Subscriber ....");
    }

    @Override
    public int getPendingMessageCount() {
        return 0;
    }
}
