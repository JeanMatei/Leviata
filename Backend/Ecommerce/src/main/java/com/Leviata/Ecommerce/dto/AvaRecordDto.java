package com.Leviata.Ecommerce.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

public record AvaRecordDto(
       int id,
       int compraId,
       int nota,
       String comentario,
       LocalDateTime dtAvaliacao
) {
}
