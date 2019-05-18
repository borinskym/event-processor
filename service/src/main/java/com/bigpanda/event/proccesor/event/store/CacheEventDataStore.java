package com.bigpanda.event.proccesor.event.store;

import com.bigpanda.event.proccesor.Statistics;
import com.bigpanda.event.proccesor.event.Event;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheEventDataStore implements EventDataStore {
    private final Map<String, Integer> eventTypeCount = new ConcurrentHashMap<>();
    private final Map<String, Integer> eventDataCount = new ConcurrentHashMap<>();

    @Override
    public void store(Event event) {
        String type = event.getEvent_type();
        String data = event.getData();
        eventTypeCount.put(type, eventTypeCount.getOrDefault(type, 0) + 1);
        eventDataCount.put(data, eventDataCount.getOrDefault(data, 0) + 1);
    }

    @Override
    public Statistics fetch() {
        return Statistics.builder()
                .eventDataCount(eventDataCount)
                .eventTypeCount(eventTypeCount)
                .build();
    }
}
