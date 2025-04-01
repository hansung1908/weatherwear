package com.hansung.weatherwear.api;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

public class OpenMeteoApiTest {

    @Test
    public void apiTest() {
        // WebClient 생성
        WebClient webClient = WebClient.create();

        // API 호출 (서울)
        String response = webClient.get()
                .uri("https://api.open-meteo.com/v1/forecast?latitude=37.566&longitude=126.9784&current_weather=true")
                .retrieve()
                .bodyToMono(String.class)
                .block();  // print를 위한 블로킹

        // 결과 출력
        System.out.println(response);
    }
}
