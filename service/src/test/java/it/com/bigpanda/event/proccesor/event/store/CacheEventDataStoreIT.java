package it.com.bigpanda.event.proccesor.event.store;

import com.bigpanda.event.proccesor.Statistics;
import com.bigpanda.event.proccesor.event.Event;
import com.bigpanda.event.proccesor.event.store.EventDataStore;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {CacheEventDataStoreIT.CacheEventDataStoreITContext.class})
public class CacheEventDataStoreIT {
    private final static long time = System.currentTimeMillis();
    @Configuration()
    @ComponentScan(basePackages = {"com.bigpanda.event.proccesor.event.store"})
    public static class CacheEventDataStoreITContext {

    }

    @Autowired
    EventDataStore eventDataStore;


    @Test
    public void shouldStoreEvent() {
        eventDataStore.store(givenEvent());

        assertThat(eventDataStore.fetch())
        .isEqualTo(expectedStatistics());
    }

    private Statistics expectedStatistics() {
        return Statistics.builder()
        .eventDataCount(ImmutableMap.of("goo", 1))
        .eventTypeCount(ImmutableMap.of("buzz", 1))
        .build();
    }

    private Event givenEvent() {
        return Event.builder().event_type("buzz").data("goo").timestamp(time).build();
    }

}