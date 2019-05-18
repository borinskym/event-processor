package com.bigpanda.event.proccesor.event.reader;

import com.bigpanda.event.proccesor.event.Event;
import com.bigpanda.event.proccesor.event.store.EventDataStore;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rx.observables.StringObservable;
import rx.schedulers.Schedulers;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

        StringObservable.from(reader)
                .subscribeOn(Schedulers.newThread())
                .subscribe((str) -> {
                    System.out.println("THIS LINE IS: " + str);
                    Event event = new Gson().fromJson(str, Event.class);
                    eventDataStore.store(event);
                });
    }
}
