package com.example.cloudusage;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class UsageService {

    public Map<String, Long> calculateTotalUsage(List<Map<String, Object>> events) {
        // Wir gruppieren die Events nach customerId und workloadId
        Map<String, Long> usageMap = new HashMap<>();

        // Gruppiere nach Kunden und Workloads
        Map<String, List<Map<String, Object>>> groupedEvents = events.stream()
                .collect(Collectors.groupingBy(event -> event.get("customerId") + "-" + event.get("workloadId")));

        // Für jeden Kunden und Workload die Nutzungszeit berechnen
        groupedEvents.forEach((key, workloadEvents) -> {
            Optional<Long> startTime = workloadEvents.stream()
                    .filter(event -> "start".equals(event.get("eventType")))
                    .map(event -> (Long) event.get("timestamp"))
                    .findFirst();

            Optional<Long> stopTime = workloadEvents.stream()
                    .filter(event -> "stop".equals(event.get("eventType")))
                    .map(event -> (Long) event.get("timestamp"))
                    .findFirst();

            if (startTime.isPresent() && stopTime.isPresent()) {
                long duration = stopTime.get() - startTime.get();
                String customerId = (String) workloadEvents.get(0).get("customerId");
                usageMap.merge(customerId, duration, Long::sum);
            }
        });

        return usageMap;
    }
}
