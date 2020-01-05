package com.rdas.common.message.observables.impl;

import com.hazelcast.config.QueueConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.ItemEvent;
import com.hazelcast.core.ItemListener;
import com.rdas.common.message.model.DatagenMessage;
import com.rdas.common.message.model.DatagridConfigProperties;
import com.rdas.common.message.model.ReportMessage;
import com.rdas.common.message.observables.MessagePublisher;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public final class DatagenMessagePublisher<T extends ReportMessage> implements MessagePublisher<DatagenMessage> {

    private IQueue<DatagenMessage> controlMessageQueue;
    private final DatagridConfigProperties datagridConfigProperties;

    public DatagenMessagePublisher(HazelcastInstance hazelcastInstance, DatagridConfigProperties datagridConfigProperties) {
        this.datagridConfigProperties = datagridConfigProperties;
        QueueConfig queueConfig = hazelcastInstance.getConfig().getQueueConfig(datagridConfigProperties.getDatagenQueueName());
        queueConfig.setMaxSize(0); // unbounded

        this.controlMessageQueue = hazelcastInstance.getQueue(datagridConfigProperties.getDatagenQueueName());
        this.controlMessageQueue.addItemListener(
                new ItemListener<>() {
                    @Override
                    public void itemAdded(ItemEvent<DatagenMessage> item) {
                        log.info("\n ** Queue DatagenMessage Added: {}", item);
                    }

                    @Override
                    public void itemRemoved(ItemEvent<DatagenMessage> item) {
                        log.info("Queue  DatagenMessage Removed: {}", item);
                    }
                }, true);
    }

    @Override
    public int getPendingMessageCount() {
        return 0;
    }

    @Override
    public void publish(ReportMessage reportMessage) {
        //TODO  - can this be done differently?
        if (reportMessage instanceof DatagenMessage) {
            controlMessageQueue.add((DatagenMessage)reportMessage);
        }
    }
}
