package com.hansung.weatherwear;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RecommendationService {

    @Autowired
    private ChatModel chatModel;  // Perplexity 또는 다른 LLM

    @Autowired
    private WeatherService weatherService;

    // 추천 생성
    public Mono<String> generateRecommendation(double latitude, double longitude) {
        String prompt = String.format(
                "현재 위치 (%.2f, %.2f)의 날씨에 맞는 옷을 추천해주세요.", latitude, longitude
        );

        return Mono.fromCallable(() -> ChatClient.create(chatModel)
                .prompt() // 프롬프트 생성
                .user(prompt) // 사용자 입력
                .call() // MCP 서버의 툴 자동 탐지
                .content()); // 응답 내용 추출
    }
}
