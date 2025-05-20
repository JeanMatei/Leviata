package com.Leviata.Ecommerce.controllers;


import com.Leviata.Ecommerce.dto.CompraRecordDto;
import com.Leviata.Ecommerce.model.CompraModel;
import com.Leviata.Ecommerce.repositories.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @PostMapping
    public ResponseEntity<CompraRecordDto>createCompra(@RequestBody CompraModel compraModel) {
        CompraModel savedCompra = compraRepository.save(compraModel);
        CompraRecordDto compraRecordDto =  CompraRecordDto.fromModel(savedCompra);
        return new ResponseEntity<>(compraRecordDto, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<CompraRecordDto>> getAllCompras() {
        List<CompraModel> compras = compraRepository.findAll();
        List<CompraRecordDto> compraRecordDtos = compras.stream()
                .map(CompraRecordDto::fromModel)
                .collect(Collectors.toList());
        return new ResponseEntity<>(compraRecordDtos, HttpStatus.OK);
    }
}
