package com.Leviata.Ecommerce.controllers;

import com.Leviata.Ecommerce.dto.ClienteRecordDto;
import com.Leviata.Ecommerce.model.AdmModel;
import com.Leviata.Ecommerce.model.ClienteModel;
import com.Leviata.Ecommerce.repositories.ClienteRepository;
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
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<ClienteModel> createCliente(@RequestBody @Valid ClienteRecordDto clienteRecordDto) {

        ClienteModel clienteModel = new ClienteModel();

        try {
            BeanUtils.copyProperties(clienteRecordDto, clienteModel);
            ClienteModel savedCliente = clienteRepository.save(clienteModel);

        } catch (BeansException e) {
            throw new RuntimeException(e);
        }

        Optional<ClienteModel> clienteExist = clienteRepository.findById(clienteModel.getClienteId());

        try {
            if (clienteExist.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(clienteExist.get());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(clienteModel));
    }

    @GetMapping
    public ResponseEntity<Iterable<ClienteModel>> getAllClientes() {

        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());
    }

    @GetMapping("/clienteId")
    public ResponseEntity<ClienteModel> getClienteById(@RequestParam int clienteId) {
        Optional<ClienteModel> clienteExist = clienteRepository.findAllByClienteId(clienteId);

        try {
            if (clienteExist.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(clienteExist.get());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(clienteExist.get());
    }

    @GetMapping("/nome")
    public ResponseEntity<ClienteModel> getClienteByNome(String nome) {
        Optional<ClienteModel> clienteExist = clienteRepository.findByNome(nome);
        try {
            if (clienteExist.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(clienteExist.get());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(clienteExist.get());
    }

    @GetMapping("/email")
    public ResponseEntity<ClienteModel> getClienteByEmail(String email) {
        Optional<ClienteModel> clienteExist = clienteRepository.findByEmail(email);
        try {
            if (clienteExist.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(clienteExist.get());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(clienteExist.get());
    }

    @GetMapping("/telefone")
    public ResponseEntity<ClienteModel> getClienteByTelefone(String telefone) {
        Optional<ClienteModel> clienteExist = clienteRepository.findByTelefone(telefone);

        try {
            if (clienteExist.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(clienteExist.get());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(clienteExist.get());
    }

    @PutMapping("/id")
    public ResponseEntity<ClienteModel> updateCliente(@RequestBody @Valid ClienteRecordDto clienteRecordDto) {
        ClienteModel clienteModel = new ClienteModel();

        try {
            BeanUtils.copyProperties(clienteRecordDto, clienteModel);
            clienteModel.setClienteId(clienteModel.getClienteId());
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
        Optional<ClienteModel> clienteExist = clienteRepository.findById(clienteModel.getClienteId());
        return ResponseEntity.status(HttpStatus.OK).body(clienteExist.get());
    }
}
