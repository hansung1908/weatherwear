package com.hansung.weatherwear.api;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

public class PerplexityApiTest {

    @Test
    public void apiTest() {
        // WebClient 생성
        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.perplexity.ai") // Perplexity API 기본 URL
                .defaultHeader("Authorization", "Bearer YOUR_API_KEY") // API 키 추가
                .defaultHeader("Content-Type", "application/json") // JSON 요청 헤더 설정
                .build();

        // API 호출 (예제 프롬프트)
        String response = webClient.post()
                .uri("/chat/completions") // Perplexity 채팅 엔드포인트
                .bodyValue("{\"model\":\"llama-3.1-sonar-small-128k-online\",\"messages\":[{\"role\":\"user\",\"content\":\"What's the weather like in Seoul?\"}]}")
                .retrieve()
                .bodyToMono(String.class) // 응답을 문자열로 변환
                .block();  // 블로킹 방식으로 결과 가져오기

        // 결과 출력
        System.out.println(response);
    }
}
