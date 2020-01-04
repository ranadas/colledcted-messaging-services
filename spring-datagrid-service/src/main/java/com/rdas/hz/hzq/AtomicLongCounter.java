package com.rdas.hz.hzq;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IAtomicLong;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class AtomicLongCounter {
    private IAtomicLong atomicLong;

    public AtomicLongCounter(HazelcastInstance hazelcastInstance) {
        atomicLong = hazelcastInstance.getAtomicLong("counter");
        log.info("Starting Counter");
    }

    @Scheduled(fixedRate = 5000L)
    public void counter() {
        log.info("AtomicLongCounter incrementing to {}", atomicLong.addAndGet(1L));
    }
}
