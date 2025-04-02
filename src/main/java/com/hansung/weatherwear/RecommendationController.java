package com.hansung.weatherwear;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping(value = "/recommend", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getRecommendation(
            @RequestParam(name = "latitude") double latitude,
            @RequestParam(name = "longitude") double longitude
    ) {
        return recommendationService.generateRecommendation(latitude, longitude);
    }
}
