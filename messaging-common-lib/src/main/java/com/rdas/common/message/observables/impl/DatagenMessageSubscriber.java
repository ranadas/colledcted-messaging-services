package com.rdas.common.message.observables.impl;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.ItemEvent;
import com.hazelcast.core.ItemListener;
import com.rdas.common.message.model.DatagenMessage;
import com.rdas.common.message.model.DatagridConfigProperties;
import com.rdas.common.message.model.ReportMessage;
import com.rdas.common.message.observables.MessageSubscriber;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public final class DatagenMessageSubscriber<T extends ReportMessage> implements MessageSubscriber<DatagenMessage> {
    private IQueue<DatagenMessage> datagenMessageQueue;
    private final DatagridConfigProperties datagridConfigProperties;


    public DatagenMessageSubscriber(HazelcastInstance hazelcastInstance,
                                    DatagridConfigProperties datagridConfigProperties) {
        this.datagridConfigProperties = datagridConfigProperties;
        datagenMessageQueue = hazelcastInstance.getQueue(datagridConfigProperties.getDatagenQueueName());


        datagenMessageQueue.addItemListener(
                new ItemListener<>() {
                    @Override
                    public void itemAdded(ItemEvent<DatagenMessage> item) {
                        log.info("\n ** datagenMessageQueue DatagenMessage Added: {}", item);
                    }

                    @Override
                    public void itemRemoved(ItemEvent<DatagenMessage> item) {
                        log.info("datagenMessageQueue DatagenMessage Removed: {}", item);
                    }
                }, true);
        log.info("\n ****** Starting DatagenMessage Subscriber ....");
    }

    @Override
    public int getPendingMessageCount() {
        return 0;
    }
}
