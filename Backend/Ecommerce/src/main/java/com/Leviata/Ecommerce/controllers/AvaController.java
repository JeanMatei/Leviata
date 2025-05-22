package com.Leviata.Ecommerce.controllers;

import com.Leviata.Ecommerce.dto.AvaRecordDto;
import com.Leviata.Ecommerce.model.AvaModel;
import com.Leviata.Ecommerce.repositories.AvaRepository;
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
@RequestMapping("/avaliacoes")
public class AvaController {

    @Autowired
    private AvaRepository avaRepository;

    @PostMapping
    public ResponseEntity<?> createAva(@RequestBody @Valid AvaRecordDto avaRecordDto) {
        try {
            AvaModel avaModel = new AvaModel();
            BeanUtils.copyProperties(avaRecordDto, avaModel);
            AvaModel saved = avaRepository.save(avaModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (BeansException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao copiar propriedades: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar avaliação: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAva() {
        try {
            List<AvaModel> list = avaRepository.findAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao listar avaliações: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAvaById(@PathVariable int id) {
        try {
            Optional<AvaModel> avaExist = avaRepository.findById(id);
            if (avaExist.isPresent()) {
                return ResponseEntity.ok(avaExist.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avaliação não encontrada.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar avaliação: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAva(@PathVariable int id, @RequestBody @Valid AvaRecordDto avaRecordDto) {
        try {
            Optional<AvaModel> avaExist = avaRepository.findById(id);
            if (avaExist.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avaliação não encontrada.");
            }
            AvaModel avaModel = avaExist.get();
            BeanUtils.copyProperties(avaRecordDto, avaModel);
            AvaModel updated = avaRepository.save(avaModel);
            return ResponseEntity.ok(updated);
        } catch (BeansException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao copiar propriedades: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar avaliação: " + e.getMessage());
        }
    }
}
