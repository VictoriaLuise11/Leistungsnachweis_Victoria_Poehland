package com.example.cloudusage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class ResultController {

    private final UsageService usageService;

    @Autowired
    public ResultController(UsageService usageService) {
        this.usageService = usageService;
    }

    @PostMapping("/v1/result")
    public Map<String, Object> calculateAndSendResult(@RequestBody Map<String, Object> request) {
        List<Map<String, Object>> events = (List<Map<String, Object>>) request.get("events");
        Map<String, Long> usageMap = usageService.calculateTotalUsage(events);

        // Senden der Ergebnisse an ein anderes System (hier nur als Platzhalter)
        usageMap.forEach((customerId, consumption) -> {
            // Hier würde das Senden an das Referenzsystem erfolgen
            System.out.printf("Sending data for customer %s with consumption %d%n", customerId, consumption);
        });

        return Map.of("result", usageMap.entrySet().stream()
                .map(entry -> Map.of("customerId", entry.getKey(), "consumption", entry.getValue()))
                .collect(Collectors.toList()));
    }
}
