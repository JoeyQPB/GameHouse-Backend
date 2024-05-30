package com.joey.gamehousesugestionservice.domain.records;

import jakarta.validation.constraints.NotBlank;

public record SuggestionRequestDTO (@NotBlank String email,
                                    @NotBlank String suggestion) {
}
