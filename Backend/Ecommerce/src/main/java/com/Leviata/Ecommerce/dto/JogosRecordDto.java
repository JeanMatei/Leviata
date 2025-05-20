package com.Leviata.Ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

public record JogosRecordDto(
        int id,
        @NotBlank String titulo,
        String capa,
        String descricao,
        String historia,
        String modalidade,
        String video_demo,
        @NotBlank Double preco,
        @NotBlank String aprovado,
        String arquivo
) {
}