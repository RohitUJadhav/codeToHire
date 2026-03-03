package org.example.codetohire.service;

import lombok.Value;
import org.example.codetohire.config.Judge0Config;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class judge0Service {

    private  final WebClient webClient;



    public judge0Service( Judge0Config  judge0Config) {
        this.webClient = WebClient.builder()
                .baseUrl(judge0Config.getBaseUrl())
                .build();
    }

    public  Map<String, Object> execute(String sourceCode, int languageId, String input){
        Map<String,Object> body = new HashMap<>();
                body.put("source_code",sourceCode);
                body.put("language_id",languageId);
               body.put("stdin",input == null ? "" : input);

        System.out.println(body);
        Map response = webClient.post()
                .uri("/submissions?base64_encoded=false&wait=true")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
        System.out.println(response.toString());
        return  response;
    }
}
