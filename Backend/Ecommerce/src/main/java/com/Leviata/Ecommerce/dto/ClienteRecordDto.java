package com.Leviata.Ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

public record ClienteRecordDto(

        int id,
        @NotBlank
        String nome,
        @NotBlank
        String email,
        @NotBlank
        String senha,
        @NotBlank
        String telefone
) {
}
