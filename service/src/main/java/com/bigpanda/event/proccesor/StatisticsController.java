package com.bigpanda.event.proccesor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Collections.emptyMap;

@RestController
@RequestMapping("/v1")
public class StatisticsController {

    @GetMapping("/stats")
    public ResponseEntity<Statistics> statistics() {
        return ResponseEntity.ok(new Statistics(emptyMap(), emptyMap()));
    }
}