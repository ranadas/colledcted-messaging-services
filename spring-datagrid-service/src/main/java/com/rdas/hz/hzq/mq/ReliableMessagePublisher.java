package com.rdas.hz.hzq.mq;

import com.hazelcast.config.QueueConfig;
import com.hazelcast.config.QueueStoreConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.ItemEvent;
import com.hazelcast.core.ItemListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Log4j2
@Component
public class ReliableMessagePublisher {
    private IQueue<ReliableMessage> reliableQueue;

    public static String QUEUE_NAME = "reliableQueue";

    public ReliableMessagePublisher(HazelcastInstance hazelcastInstance, ReliableQueueStore store) {
        QueueConfig queueConfig = hazelcastInstance.getConfig().getQueueConfig(QUEUE_NAME);
        queueConfig.setMaxSize(0); // unbounded

        QueueStoreConfig queueStoreConfig = new QueueStoreConfig().setStoreImplementation(store);
        queueStoreConfig.setProperty("memory-limit", "0");

        queueConfig.setQueueStoreConfig(queueStoreConfig);

        this.reliableQueue = hazelcastInstance.getQueue( QUEUE_NAME );
        this.reliableQueue.addItemListener(
                new ItemListener<ReliableMessage>() {
                    @Override
                    public void itemAdded(ItemEvent<ReliableMessage> item) {
                        log.info("ReliableQueue itemAdded: {}", item);
                    }

                    @Override
                    public void itemRemoved(ItemEvent<ReliableMessage> item) {
                        log.info("ReliableQueue itemRemoved: {}", item);
                    }
                }, true);

        this.reliableQueue = hazelcastInstance.getQueue(QUEUE_NAME);
        log.info("Starting ReliableMessagePublisher");
    }

    private static AtomicLong count = new AtomicLong();

    @Scheduled(fixedRate = 1000L)
    public void publish() {
        reliableQueue.add(new ReliableMessage(count.incrementAndGet(), "Publishing ReliableMessage "));
    }
}
