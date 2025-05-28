package com.Leviata.Ecommerce.controllers;

import com.Leviata.Ecommerce.dto.ClienteRecordDto;
import com.Leviata.Ecommerce.model.ClienteModel;
import com.Leviata.Ecommerce.repositories.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<ClienteModel> createCliente(@RequestBody @Valid ClienteRecordDto clienteRecordDto) {
        ClienteModel clienteModel = new ClienteModel();
        BeanUtils.copyProperties(clienteRecordDto, clienteModel);
        ClienteModel savedCliente = clienteRepository.save(clienteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
    }

    @GetMapping
    public ResponseEntity<Iterable<ClienteModel>> getAllClientes() {
        return ResponseEntity.ok(clienteRepository.findAll());
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteModel> getClienteById(@PathVariable int clienteId) {
        Optional<ClienteModel> clienteExist = clienteRepository.findAllByClienteId(clienteId);
        return clienteExist.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/nome")
    public ResponseEntity<ClienteModel> getClienteByNome(@RequestParam String nome) {
        Optional<ClienteModel> clienteExist = clienteRepository.findByNome(nome);
        return clienteExist.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/email")
    public ResponseEntity<ClienteModel> getClienteByEmail(@RequestParam String email) {
        Optional<ClienteModel> clienteExist = clienteRepository.findByEmail(email);
        return clienteExist.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/telefone")
    public ResponseEntity<ClienteModel> getClienteByTelefone(@RequestParam String telefone) {
        Optional<ClienteModel> clienteExist = clienteRepository.findByTelefone(telefone);
        return clienteExist.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteModel> updateCliente(@PathVariable int clienteId,
                                                      @RequestBody @Valid ClienteRecordDto clienteRecordDto) {
        Optional<ClienteModel> clienteExist = clienteRepository.findById(clienteId);
        if (clienteExist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ClienteModel clienteModel = clienteExist.get();
        BeanUtils.copyProperties(clienteRecordDto, clienteModel);
        ClienteModel updatedCliente = clienteRepository.save(clienteModel);

        return ResponseEntity.ok(updatedCliente);
    }
}
