package com.pfe.ai.ai.model.dto;

import com.pfe.ai.ai.model.Option;

import java.util.List;

public record ExamQuestionDTO(
        Long id,
        String text,
        String answer,
        List<Option> options
) {
}
