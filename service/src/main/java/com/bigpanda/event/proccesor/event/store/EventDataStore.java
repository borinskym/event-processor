package com.bigpanda.event.proccesor.event.store;

import com.bigpanda.event.proccesor.Statistics;
import com.bigpanda.event.proccesor.event.Event;

public interface EventDataStore {
    void store(Event event);
    Statistics fetch();
}
