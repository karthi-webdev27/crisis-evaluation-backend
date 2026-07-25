// File: src/main/java/com/example/crisis/controller/CrisisEvaluationController.java
package com.example.crisis.controller;

import com.example.crisis.dto.CrisisEvaluationRequest;
import com.example.crisis.dto.CrisisEvaluationResponse;
import com.example.crisis.service.CrisisEvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1")
public class CrisisEvaluationController {

    private final CrisisEvaluationService crisisEvaluationService;

    public CrisisEvaluationController(CrisisEvaluationService crisisEvaluationService) {
        this.crisisEvaluationService = crisisEvaluationService;
    }

    @PostMapping("/analyze-state")
    public CompletableFuture<ResponseEntity<CrisisEvaluationResponse>> analyzeState(
            @RequestBody CrisisEvaluationRequest request) {
        
        return crisisEvaluationService.analyzeState(request.transcribedAudio())
                .thenApply(ResponseEntity::ok);
    }
}