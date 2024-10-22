package com.example.cloudusage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@RestController
public class DatasetController {

    @GetMapping("/v1/dataset")
    public Map<String, Object> getDataset() {
        // Hier simulieren wir das Lesen der Rohdaten
        List<Map<String, Object>> events = new ArrayList<>();
        events.add(Map.of(
                "customerId", "342a55a3-b138-4665-8351-111b28833d34",
                "workloadId", "53ddf5cf-7a12-4b7f-8751-48757774c0c5",
                "timestamp", 1699872883000L,
                "eventType", "start"
        ));
        events.add(Map.of(
                "customerId", "342a55a3-b138-4665-8351-111b28833d34",
                "workloadId", "53ddf5cf-7a12-4b7f-8751-48757774c0c5",
                "timestamp", 1700401984000L,
                "eventType", "stop"
        ));

        return Map.of("events", events);
    }
}
