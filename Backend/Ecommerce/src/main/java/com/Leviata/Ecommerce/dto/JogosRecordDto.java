package com.Leviata.Ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JogosRecordDto(

        @NotBlank String aprovado,
        String arquivo,
        String capa,
        String descricao,
        String historia,
        String modalidade,
        @NotNull Double preco,
        @NotBlank String titulo,
        String video_demo

) {}
