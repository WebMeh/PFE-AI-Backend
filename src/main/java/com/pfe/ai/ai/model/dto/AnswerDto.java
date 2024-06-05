package com.pfe.ai.ai.model.dto;

public record AnswerDto(
        Long userId,
        Long questionId,
        String content
) {
}
