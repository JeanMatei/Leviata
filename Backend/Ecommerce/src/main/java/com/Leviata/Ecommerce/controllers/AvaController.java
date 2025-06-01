package com.Leviata.Ecommerce.controllers;

import com.Leviata.Ecommerce.dto.AvaRecordDto;
import com.Leviata.Ecommerce.model.AvaModel;
import com.Leviata.Ecommerce.repositories.AvaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacoes")
@CrossOrigin(origins = "*")
public class AvaController {

    @Autowired
    private AvaRepository avaRepository;

    @PostMapping
    public ResponseEntity<AvaModel> createAva(@RequestBody @Valid AvaRecordDto avaRecordDto) {
        AvaModel avaModel = new AvaModel();
        BeanUtils.copyProperties(avaRecordDto, avaModel);
        AvaModel saved = avaRepository.save(avaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<AvaModel>> getAllAva() {
        List<AvaModel> list = avaRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAvaById(@PathVariable int id) {
        Optional<AvaModel> avaExist = avaRepository.findById(id);
        return avaExist.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avaliação não encontrada."));
    }

    @GetMapping("/compra/{idCompra}")
    public ResponseEntity<Object> getAvaByCompraId(@PathVariable int idCompra) {
        Optional<AvaModel> avaOpt = avaRepository.findByidCompra(idCompra);
        return avaOpt.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avaliação para compra não encontrada."));
    }

    @GetMapping("/nota/{nota}")
    public ResponseEntity<Object> getAvaByNota(@PathVariable int nota) {
        Optional<AvaModel> avaOpt = avaRepository.findByNota(nota);
        return avaOpt.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avaliação com nota informada não encontrada."));
    }

    @GetMapping("/data-after/{data}")
    public ResponseEntity<Object> getAvaByDataAvaliacaoAfter(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime data) {

        Optional<AvaModel> avaOpt = avaRepository.findByDtAvaliacaoAfter(data);
        return avaOpt.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma avaliação encontrada após a data informada."));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAva(@PathVariable int id, @RequestBody @Valid AvaRecordDto avaRecordDto) {
        Optional<AvaModel> avaExist = avaRepository.findById(id);
        if (avaExist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avaliação não encontrada.");
        }
        AvaModel avaModel = avaExist.get();
        BeanUtils.copyProperties(avaRecordDto, avaModel);
        AvaModel updated = avaRepository.save(avaModel);
        return ResponseEntity.ok(updated);
    }
}
