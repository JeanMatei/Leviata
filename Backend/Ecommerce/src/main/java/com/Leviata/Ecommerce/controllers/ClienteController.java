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
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<Object> createCliente(@RequestBody @Valid ClienteRecordDto clienteRecordDto) {
        Optional<ClienteModel> clienteExist = clienteRepository.findByEmail(clienteRecordDto.email());

        if (clienteExist.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cliente com este e-mail já existe.");
        }

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
    public ResponseEntity<Object> getClienteById(@PathVariable int clienteId) {
        Optional<ClienteModel> clienteExist = clienteRepository.findAllByClienteId(clienteId);

        if (clienteExist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }

        return ResponseEntity.ok(clienteExist.get());
    }

    @GetMapping("/nome")
    public ResponseEntity<Object> getClienteByNome(@RequestParam String nome) {
        Optional<ClienteModel> clienteExist = clienteRepository.findByNome(nome);

        if (clienteExist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }

        return ResponseEntity.ok(clienteExist.get());
    }

    @GetMapping("/email")
    public ResponseEntity<Object> getClienteByEmail(@RequestParam String email) {
        Optional<ClienteModel> clienteExist = clienteRepository.findByEmail(email);

        if (clienteExist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }

        return ResponseEntity.ok(clienteExist.get());
    }

    @GetMapping("/telefone")
    public ResponseEntity<Object> getClienteByTelefone(@RequestParam String telefone) {
        Optional<ClienteModel> clienteExist = clienteRepository.findByTelefone(telefone);

        if (clienteExist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }

        return ResponseEntity.ok(clienteExist.get());
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Object> updateCliente(@PathVariable int clienteId,
                                                @RequestBody @Valid ClienteRecordDto clienteRecordDto) {
        Optional<ClienteModel> clienteExist = clienteRepository.findById(clienteId);

        if (clienteExist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }

        ClienteModel clienteModel = clienteExist.get();
        BeanUtils.copyProperties(clienteRecordDto, clienteModel);
        ClienteModel updatedCliente = clienteRepository.save(clienteModel);

        return ResponseEntity.ok(updatedCliente);
    }
}
