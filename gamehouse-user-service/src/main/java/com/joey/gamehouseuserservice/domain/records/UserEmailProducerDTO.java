package com.joey.gamehouseuserservice.domain.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserEmailProducerDTO (@NotBlank @Email String email,
                                    @NotBlank String role,
                                    @NotBlank Long id){
}
