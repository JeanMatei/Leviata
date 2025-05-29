package com.Leviata.Ecommerce.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AvaRecordDto(

       @NotNull int idCompra,
       @NotNull int nota,
       @NotBlank String comentario,
       @NotNull LocalDateTime dtAvaliacao
) {


}
