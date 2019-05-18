package com.bigpanda.event.proccesor.event.reader;

import com.bigpanda.event.proccesor.event.Event;
import com.bigpanda.event.proccesor.event.store.EventDataStore;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rx.observables.StringObservable;
import rx.schedulers.Schedulers;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
@Slf4j
@Service
public class FileEventStreamReader {
    private final EventDataStore eventDataStore;
    private final String eventStreamFileLocation;

    @Autowired
    public FileEventStreamReader(EventDataStore eventDataStore, @Value("${streamFileLocation}") String eventStreamFileLocation) {
        this.eventDataStore = eventDataStore;
        this.eventStreamFileLocation = eventStreamFileLocation;
    }

    @PostConstruct
    private void postConstruct() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", eventStreamFileLocation);
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));

        StringObservable.byLine(StringObservable.from(reader))
                .subscribeOn(Schedulers.newThread())
                .subscribe((line) -> {
                    log.info("got line {}", line);
                    try {
                        Event event = new Gson().fromJson(line, Event.class);
                        eventDataStore.store(event);
                    } catch (JsonSyntaxException e) {
                        log.error("message is not valid json", e);
                    }
                });
    }
}
