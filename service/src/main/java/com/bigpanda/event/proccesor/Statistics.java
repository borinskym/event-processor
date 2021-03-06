package com.bigpanda.event.proccesor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Statistics {
    Map<String, Integer> eventTypeCount;
    Map<String, Integer> eventDataCount;
}
