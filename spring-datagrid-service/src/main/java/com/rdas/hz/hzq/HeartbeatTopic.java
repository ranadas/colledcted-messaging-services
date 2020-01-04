package com.rdas.hz.hzq;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Log4j2
@Component
public class HeartbeatTopic {
    public final static UUID uuid = UUID.randomUUID();
    private ITopic hearbeatTopic;

    public HeartbeatTopic(HazelcastInstance hazelcastInstance){
        hearbeatTopic = hazelcastInstance.getTopic( "heartbeatTopic" );
        hearbeatTopic.addMessageListener(msg -> {
            log.info("Heartbeat Received: {}", msg.getMessageObject());
        });

        log.info("Starting Heartbeat Topic");
    }

    @Scheduled(fixedRate = 5000L)
    public void heartbeat(){
        hearbeatTopic.publish(uuid);
    }
}
