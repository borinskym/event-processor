package it.com.bigpanda.event.proccesor.event.reader;

import com.bigpanda.event.proccesor.event.Event;
import com.bigpanda.event.proccesor.event.reader.FileEventStreamReader;
import com.bigpanda.event.proccesor.event.store.EventDataStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {FileEventStreamReaderIT.FileEventStreamReaderITContext.class},
        properties = "streamFileLocation=src/test/resources/generator")
public class FileEventStreamReaderIT {

    @Configuration()
    @ComponentScan(basePackages = {"com.bigpanda.event.proccesor.event.reader"})
    public static class FileEventStreamReaderITContext {

        @Bean
        public EventDataStore eventDataStore(){
            return mock(EventDataStore.class);
        }
    }

    @Autowired
    FileEventStreamReader fileEventStreamReader;

    @Autowired
    EventDataStore eventDataStore;

    @Test
    public void shouldReadStreamEventIntoStoreWhileIgnoringDamagedLine() {
        verify(eventDataStore).store(expectedEvent());
    }

    private Event expectedEvent() {
        return Event.builder().event_type("baz").data("amet").timestamp(1558167148L).build();
    }

}