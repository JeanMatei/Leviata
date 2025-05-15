package com.Leviata.Ecommerce.dto;

import jakarta.validation.constraints.Pattern;

public record DevsRecordDto(
        String nm_empresa,
        String nm_Responsavel,
        String email,
        @Pattern(regexp = "\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}", message = "WhatsApp deve estar no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX") String whatsApp
) {
}
