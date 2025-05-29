package com.Leviata.Ecommerce.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CompraRecordDto(
        @NotNull int clienteId,
        @NotNull int jogosId,
        @NotNull LocalDateTime dt_Compra,
        @NotNull Double vl_Compra
) {}
