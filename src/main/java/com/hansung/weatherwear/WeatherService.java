package com.hansung.weatherwear;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    private final WebClient webClient;

    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.open-meteo.com/v1/forecast").build();
    }

    @Tool(description = "위도와 경도를 통한 현재 날씨 호출")
    public Mono<WeatherData> getWeather(
            @ToolParam(description = "Latitude") double latitude,
            @ToolParam(description = "Longitude") double longitude
    ) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam("current_weather", true)
                        .build()
                )
                .retrieve()
                .bodyToMono(WeatherData.class);
    }
}
