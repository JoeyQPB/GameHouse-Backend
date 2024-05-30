package com.joey.gamehousesugestionservice.domain.records;

import jakarta.validation.constraints.NotBlank;

public record SuggestionConsumerDTO (@NotBlank Long id,
                                     @NotBlank String email,
                                     @NotBlank String suggestion,
                                     @NotBlank Boolean consumed) {
}
