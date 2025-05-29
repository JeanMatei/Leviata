package com.Leviata.Ecommerce.controllers;

import com.Leviata.Ecommerce.dto.CompraRecordDto;
import com.Leviata.Ecommerce.model.ClienteModel;
import com.Leviata.Ecommerce.model.CompraModel;
import com.Leviata.Ecommerce.model.JogosModel;
import com.Leviata.Ecommerce.repositories.ClienteRepository;
import com.Leviata.Ecommerce.repositories.CompraRepository;
import com.Leviata.Ecommerce.repositories.JogosRepository;
import jakarta.validation.Valid;
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

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private JogosRepository jogosRepository;

    @PostMapping
    public ResponseEntity<Object> createCompra(@RequestBody @Valid CompraRecordDto compraRecordDto) {

        Optional<ClienteModel> clienteOpt = clienteRepository.findById(compraRecordDto.clienteId());
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        Optional<JogosModel> jogoOpt = jogosRepository.findById(compraRecordDto.jogosId());
        if (jogoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogo não encontrado");
        }

        CompraModel compraModel = new CompraModel();
        compraModel.setCliente(clienteOpt.get());
        compraModel.setJogo(jogoOpt.get());
        compraModel.setDt_Compra(compraRecordDto.dt_Compra());
        compraModel.setVl_Compra(compraRecordDto.vl_Compra());

        CompraModel savedCompra = compraRepository.save(compraModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompra);
    }

    @GetMapping
    public ResponseEntity<List<CompraModel>> getAllCompras() {
        List<CompraModel> compras = compraRepository.findAll();
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCompraById(@PathVariable int id) {
        Optional<CompraModel> compraOpt = compraRepository.findById(id);
        if (compraOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compra não encontrada");
        }
        return ResponseEntity.ok(compraOpt.get());
    }

    @PutMapping("/{idCompra}")
    public ResponseEntity<Object> updateCompra(@PathVariable int idCompra,
                                               @RequestBody @Valid CompraRecordDto compraRecordDto) {

        Optional<CompraModel> compraOpt = compraRepository.findById(idCompra);
        if (compraOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compra não encontrada");
        }

        Optional<ClienteModel> clienteOpt = clienteRepository.findById(compraRecordDto.clienteId());
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        Optional<JogosModel> jogoOpt = jogosRepository.findById(compraRecordDto.jogosId());
        if (jogoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogo não encontrado");
        }

        CompraModel compraModel = compraOpt.get();
        compraModel.setCliente(clienteOpt.get());
        compraModel.setJogo(jogoOpt.get());
        compraModel.setDt_Compra(compraRecordDto.dt_Compra());
        compraModel.setVl_Compra(compraRecordDto.vl_Compra());

        CompraModel updatedCompra = compraRepository.save(compraModel);
        return ResponseEntity.ok(updatedCompra);
    }
}
