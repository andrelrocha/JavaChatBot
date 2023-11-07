package io.cronapp.ia.controller;

import io.cronapp.ia.DTO.UserInput;
import io.cronapp.ia.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private OpenAIService openAIService;

    @PostMapping
    public ResponseEntity<String> callingAI(@RequestBody UserInput userInput) {
        System.out.println(userInput);
        var response = openAIService.openAIServiceCall(userInput.input());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public String callingAiVidep(@RequestParam String userInput) {
        var response = openAIService.openAIServiceCall(userInput);
        return response;
    }
}
