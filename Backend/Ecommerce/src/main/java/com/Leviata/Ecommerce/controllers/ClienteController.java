package com.Leviata.Ecommerce.controllers;

import com.Leviata.Ecommerce.dto.ClienteRecordDto;
import com.Leviata.Ecommerce.model.ClienteModel;
import com.Leviata.Ecommerce.repositories.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<ClienteModel> createCliente(@RequestBody @Valid ClienteRecordDto clienteRecordDto) {

        ClienteModel clienteModel = new ClienteModel();

        try {
            BeanUtils.copyProperties(clienteRecordDto, clienteModel);
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }

        Optional<ClienteModel> clienteExist = clienteRepository.findById(clienteModel.getId());

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
    public ResponseEntity<List<ClienteModel>> getAllClientes() {

        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());
    }

    @GetMapping
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

    @GetMapping
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

    @GetMapping
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

    public ResponseEntity<ClienteModel> updateCliente(@RequestBody @Valid ClienteRecordDto clienteRecordDto) {
        ClienteModel clienteModel = new ClienteModel();

        try {
            BeanUtils.copyProperties(clienteRecordDto, clienteModel);
            clienteModel.setId(clienteModel.getId());
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
        Optional<ClienteModel> clienteExist = clienteRepository.findById(clienteModel.getId());
        return ResponseEntity.status(HttpStatus.OK).body(clienteExist.get());
    }
}
