package com.bigpanda.event.proccesor;

import com.bigpanda.event.proccesor.event.store.EventDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class StatisticsController {

    @Autowired
    EventDataStore eventDataStore;

    @GetMapping("/stats")
    public ResponseEntity<Statistics> statistics() {
        return ResponseEntity.ok(eventDataStore.fetch());
    }
}