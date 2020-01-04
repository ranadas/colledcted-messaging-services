package com.rdas.hz.hzq.mq;

import com.hazelcast.core.QueueStore;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Component
public class ReliableQueueStore implements QueueStore<ReliableMessage> {
    @Override
    public void store(Long aLong, ReliableMessage reliableMessage) {

    }

    @Override
    public void storeAll(Map<Long, ReliableMessage> map) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void deleteAll(Collection<Long> collection) {

    }

    @Override
    public ReliableMessage load(Long aLong) {
        return null;
    }

    @Override
    public Map<Long, ReliableMessage> loadAll(Collection<Long> collection) {
        return null;
    }

    @Override
    public Set<Long> loadAllKeys() {
        return null;
    }
}
