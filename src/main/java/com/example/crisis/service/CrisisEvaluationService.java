// File: src/main/java/com/example/crisis/service/CrisisEvaluationService.java
package main.java.com.example.crisis.service;

import com.example.crisis.dto.CrisisEvaluationResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CrisisEvaluationService {

    private static final String GEMINI_API_INSTANCE = "geminiApi";

    @CircuitBreaker(name = GEMINI_API_INSTANCE, fallbackMethod = "fallbackAnalyzeState")
    @TimeLimiter(name = GEMINI_API_INSTANCE, fallbackMethod = "fallbackAnalyzeState")
    public CompletableFuture<CrisisEvaluationResponse> analyzeState(String transcribedAudio) {
        return CompletableFuture.supplyAsync(() -> callGeminiApi(transcribedAudio));
    }

    /**
     * Placeholder method representing a call to the Gemini API.
     * Replace the contents of this method with the generated Gemini API snippet.
     */
    private CrisisEvaluationResponse callGeminiApi(String transcribedAudio) {
        // TODO: Implement actual Gemini API call here
        
        // Simulated successful response for placeholder purposes
        return new CrisisEvaluationResponse(
                "Low",
                "Calm",
                "Thank you for sharing. How can I support you further today?",
                false
        );
    }

    /**
     * Fallback method triggered on CircuitBreaker OPEN state or TimeLimiter timeout.
     */
    private CompletableFuture<CrisisEvaluationResponse> fallbackAnalyzeState(String transcribedAudio, Throwable throwable) {
        CrisisEvaluationResponse fallbackResponse = new CrisisEvaluationResponse(
                "High",
                "Unknown/Offline",
                "We are having trouble connecting, but you are not alone. Please take a deep breath and call the emergency support line immediately.",
                true
        );
        return CompletableFuture.completedFuture(fallbackResponse);
    }
}