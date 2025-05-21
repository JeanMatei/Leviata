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

        Optional<ClienteModel> clienteExist =clienteRepository.findById(clienteModel.getId());

        try {
            if (clienteExist.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(clienteExist.get());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(clienteModel));
    }
}
