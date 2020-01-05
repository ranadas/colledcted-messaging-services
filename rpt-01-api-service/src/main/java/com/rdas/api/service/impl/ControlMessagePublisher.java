package com.rdas.api.service.impl;

import com.hazelcast.config.QueueConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.ItemEvent;
import com.hazelcast.core.ItemListener;
import com.rdas.common.message.model.ControlMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ControlMessagePublisher {
    private IQueue<ControlMessage> controlMessageQueue;

    @Value("${reporting.queues.control-queue-name}")
    private String queueName;

    // TODO  - name from config
    public static String QUEUE_NAME = "REA_QUEUE";

    public ControlMessagePublisher(HazelcastInstance hazelcastInstance) {
        QueueConfig queueConfig = hazelcastInstance.getConfig().getQueueConfig(QUEUE_NAME);
        queueConfig.setMaxSize(0); // unbounded

        this.controlMessageQueue = hazelcastInstance.getQueue( QUEUE_NAME );
        this.controlMessageQueue.addItemListener(
                new ItemListener<>() {
                    @Override
                    public void itemAdded(ItemEvent<ControlMessage> item) {
                        log.info("Queue ControlMessage Added: {}", item);
                    }

                    @Override
                    public void itemRemoved(ItemEvent<ControlMessage> item) {
                        log.info("Queue  ControlMessage Removed: {}", item);
                    }
                }, true);
    }

    public void publish(ControlMessage controlMessage) {
        controlMessageQueue.add(controlMessage);
        //reliableQueue.add(new ReliableMessage("Publishing ReliableMessage " + count.incrementAndGet()));
    }
}
