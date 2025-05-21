package com.Leviata.Ecommerce.controllers;

import com.Leviata.Ecommerce.model.CompraModel;
import com.Leviata.Ecommerce.repositories.CompraRepository;
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
    public ResponseEntity<CompraModel> createCompra(@RequestBody CompraModel compraModel) {
        CompraModel savedCompra = compraRepository.save(compraModel);
        return new ResponseEntity<>(savedCompra, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CompraModel>> getAllCompras() {
        List<CompraModel> compras = compraRepository.findAll();
        return new ResponseEntity<>(compras, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraModel> getCompraById(@PathVariable int id) {
        Optional<CompraModel> compraModel = compraRepository.findById(id);
        return compraModel.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraModel> updateCompra(@PathVariable int id, @RequestBody CompraModel compraModel) {
        if (!compraRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        compraModel.setId(id);
        CompraModel updatedCompra = compraRepository.save(compraModel);
        return new ResponseEntity<>(updatedCompra, HttpStatus.OK);
    }
}
