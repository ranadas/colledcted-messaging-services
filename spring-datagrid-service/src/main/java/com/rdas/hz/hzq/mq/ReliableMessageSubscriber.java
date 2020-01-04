package com.rdas.hz.hzq.mq;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.ItemEvent;
import com.hazelcast.core.ItemListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Log4j2
@Component
public class ReliableMessageSubscriber {
    private IQueue<ReliableMessage> reliableQueue;

    public ReliableMessageSubscriber(HazelcastInstance hazelcastInstance) {
        this.reliableQueue = hazelcastInstance.getQueue(ReliableMessagePublisher.QUEUE_NAME);
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
        log.info("Starting ReliableMessageSubscriber");
    }

    private static AtomicLong count = new AtomicLong();

    @Scheduled(fixedRate = 5000L)
    public void publish() {
        List<ReliableMessage> receivedMessages = new ArrayList<ReliableMessage>();
        reliableQueue.drainTo(receivedMessages);

        log.info("Drained {} messages: {}",
                receivedMessages.size(),
                receivedMessages.stream()
                        .map(ReliableMessage::getMessage)
                        .collect(Collectors.joining(",")));
    }
}
