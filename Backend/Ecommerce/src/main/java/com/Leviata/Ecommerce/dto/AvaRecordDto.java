package com.Leviata.Ecommerce.dto;


import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AvaRecordDto(

       @NotBlank int compraId,
       @NotBlank int nota,
       @NotBlank String comentario,
       @NotBlank LocalDateTime dtAvaliacao
) {


}
