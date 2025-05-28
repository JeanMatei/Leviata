package com.Leviata.Ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClienteRecordDto(


        @NotBlank String nome,
        @NotBlank String email,
        @NotBlank String senha,
        @NotBlank @Pattern(regexp = "\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}", message = "Telefone deve estar no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX") String telefone

) {
}
