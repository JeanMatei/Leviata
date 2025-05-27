package com.Leviata.Ecommerce.controllers;

import com.Leviata.Ecommerce.dto.CompraRecordDto;
import com.Leviata.Ecommerce.model.AdmModel;
import com.Leviata.Ecommerce.model.CompraModel;
import com.Leviata.Ecommerce.repositories.CompraRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @PostMapping
    public ResponseEntity<CompraModel> createCompra(@RequestBody @Valid CompraRecordDto compraRecordDto) {
        CompraModel compraModel = new CompraModel();
        try {
            BeanUtils.copyProperties(compraRecordDto, compraModel);
            CompraModel savedcompra = compraRepository.save(compraModel);

        } catch (BeansException e) {
            throw new RuntimeException("Erro ao copiar propriedades do DTO para o Model", e);
        }
        CompraModel savedCompra = compraRepository.save(compraModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompra);
    }

    @GetMapping
    public ResponseEntity<List<CompraModel>> getAllCompras() {
        List<CompraModel> compras;
        try {
            compras = compraRepository.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao buscar todas as compras", e);
        }
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraModel> getCompraById(@PathVariable int id) {
        Optional<CompraModel> compra;
        try {
            compra = compraRepository.findById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao buscar compra por ID", e);
        }

        if (compra.isPresent()) {
            return ResponseEntity.ok(compra.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraModel> updateCompra(@PathVariable int id,
                                                    @RequestBody @Valid CompraRecordDto compraRecordDto) {
        Optional<CompraModel> compraExistente;
        try {
            compraExistente = compraRepository.findById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao buscar compra para atualização", e);
        }

        if (compraExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        CompraModel compraModel = compraExistente.get();
        try {
            BeanUtils.copyProperties(compraRecordDto, compraModel);
        } catch (BeansException e) {
            throw new RuntimeException("Erro ao copiar propriedades do DTO para o Model", e);
        }

        CompraModel compraAtualizada = compraRepository.save(compraModel);
        return ResponseEntity.ok(compraAtualizada);
    }
}
