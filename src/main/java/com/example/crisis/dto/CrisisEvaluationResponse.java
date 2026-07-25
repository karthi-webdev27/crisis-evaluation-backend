// File: src/main/java/com/example/crisis/dto/CrisisEvaluationResponse.java
package com.example.crisis.dto;

public record CrisisEvaluationResponse(
    String riskLevel,
    String primaryEmotion,
    String deEscalationScript,
    boolean requiresEmergencyUI
) {}