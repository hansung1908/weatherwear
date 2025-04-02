package com.hansung.weatherwear;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class RecommendationService {

    @Autowired
    private ChatModel chatModel;  // Perplexity 또는 다른 LLM

    @Autowired
    private ToolCallbackProvider toolCallbackProvider;

    // 추천 생성
    public Flux<String> generateRecommendation(double latitude, double longitude) {
        String prompt = "제공된 날씨 정보를 참고하여 적절한 옷차림을 추천해주세요.";

        return ChatClient.create(chatModel)
                .prompt() // 프롬프트 생성
                .user(prompt) // 사용자 입력
                .tools(toolCallbackProvider.getToolCallbacks())// 툴 등록
                .stream()
                .content(); // 응답 내용 추출
    }
}
