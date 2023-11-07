package io.cronapp.ia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class OpenAIService {
    @Value("${openai.apiKey}")
    private String apiKey;

    @Value("${openai.modelId}")
    private String modelId;

    @Value("${openai.url}")
    private String url;



    private final RestTemplate restTemplate;
    @Autowired
    public OpenAIService (RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String openAIServiceCall(String userInput) {

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        System.out.println(headers);

        var requestBody = "{\"model\": \"" + modelId +
                "\", \"messages\": [{\"role\": \"user\", \"content\": \"" +
                userInput + "\"}]}";

        var request = new HttpEntity<>(requestBody, headers);

        var response = restTemplate.postForObject(url, request, String.class);

        return response;

    }

}
