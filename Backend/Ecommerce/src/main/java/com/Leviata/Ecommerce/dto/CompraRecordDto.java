package com.Leviata.Ecommerce.dto;

import com.Leviata.Ecommerce.model.CompraModel;
import com.Leviata.Ecommerce.model.CompraModel.FormaPagamento;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CompraRecordDto(

        @NotBlank int cliente_Id,
        @NotBlank int jogo_Id,
        @NotBlank LocalDateTime dataCompra,
        @NotBlank FormaPagamento formaPagamento
){
}

