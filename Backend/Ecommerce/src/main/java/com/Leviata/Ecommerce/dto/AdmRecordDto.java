package com.Leviata.Ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

public record AdmRecordDto(
        int id,

        @NotBlank
        String nome,

        String email,

        @NotBlank
        String senha
) {
}
