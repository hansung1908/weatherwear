package com.hansung.weatherwear;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/recommend")
    public Mono<String> getRecommendation(
            @RequestParam double latitude,
            @RequestParam double longitude
    ) {
        return recommendationService.generateRecommendation(latitude, longitude);
    }
}
