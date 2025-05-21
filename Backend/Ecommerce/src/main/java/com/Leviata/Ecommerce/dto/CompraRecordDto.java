package com.Leviata.Ecommerce.dto;

import com.Leviata.Ecommerce.model.CompraModel;
import com.Leviata.Ecommerce.model.CompraModel.FormaPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CompraRecordDto(
        int id,
        int clienteId,
        int jogoId,
        LocalDateTime dataCompra,
        BigDecimal precoPago,
        FormaPagamento formaPagamento
)
}

