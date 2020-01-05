package com.rdas.datagatherer.service.impl;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.ItemEvent;
import com.hazelcast.core.ItemListener;
import com.rdas.common.message.model.DatagenMessage;
import com.rdas.common.message.model.DatagridConfigProperties;
import com.rdas.common.message.model.ReportMessage;
import com.rdas.common.message.observables.MessageSubscriber;
import com.rdas.datagatherer.service.DataGathererService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public final class DatagenMessageSubscriber<T extends ReportMessage> implements MessageSubscriber<DatagenMessage> {
    private IQueue<DatagenMessage> datagenMessageQueue;
    private final DatagridConfigProperties datagridConfigProperties;
    private final DataGathererService dataGathererService;

    public DatagenMessageSubscriber(HazelcastInstance hazelcastInstance,
                                    DatagridConfigProperties datagridConfigProperties,
                                    DataGathererService dataGathererService) {
        this.datagridConfigProperties = datagridConfigProperties;
        this.dataGathererService = dataGathererService;
        datagenMessageQueue = hazelcastInstance.getQueue(datagridConfigProperties.getDatagenQueueName());

        datagenMessageQueue.addItemListener(
                new ItemListener<>() {
                    @Override
                    public void itemAdded(ItemEvent<DatagenMessage> item) {
                        log.info("\n ** datagenMessageQueue DatagenMessage Added: {}", item);
                        dataGathererService.processStep(item.getItem());
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
