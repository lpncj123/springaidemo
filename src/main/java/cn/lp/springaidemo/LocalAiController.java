package cn.lp.springaidemo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class LocalAiController {

    private final ChatClient chatClient;

    public LocalAiController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return chatClient.prompt()
            .user(message)
            .call()
            .content();
    }

    @GetMapping("/code")
    public String generateCode() {
        String prompt = "用 Spring Boot 写一个简单的 REST 接口，返回 'Hello from local Ollama'";

        return chatClient.prompt()
            .user(prompt)
            .call()
            .content();
    }
}