package com.Leviata.Ecommerce.controllers;

import com.Leviata.Ecommerce.dto.AdmRecordDto;
import com.Leviata.Ecommerce.model.AdmModel;
import com.Leviata.Ecommerce.repositories.AdmRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adm")
@CrossOrigin(origins = "*")
public class AdmController {

    @Autowired
    private AdmRepository admRepository;

    @PostMapping
    public ResponseEntity<AdmModel> createAdm(@RequestBody @Valid AdmRecordDto admRecordDto) {
        AdmModel admModel = new AdmModel();
        BeanUtils.copyProperties(admRecordDto, admModel);
        AdmModel savedAdm = admRepository.save(admModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdm);
    }

    @GetMapping
    public ResponseEntity<List<AdmModel>> getAllAdm() {
        return ResponseEntity.ok(admRepository.findAll());
    }

    @GetMapping("/{idadm}")
    public ResponseEntity<AdmModel> getAdmById(@PathVariable int idadm) {
        Optional<AdmModel> adm = admRepository.findById(idadm);
        if (adm.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(adm.get());
    }

    @GetMapping("/email")
    public ResponseEntity<AdmModel> getAdmByEmail(@RequestParam String email) {
        Optional<AdmModel> adm = admRepository.findByEmail(email);
        if (adm.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(adm.get());
    }

    @GetMapping("/nome")
    public ResponseEntity<AdmModel> getAdmByNome(@RequestParam String nome) {
        Optional<AdmModel> adm = admRepository.findByNome(nome);
        if (adm.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(adm.get());
    }

    @PutMapping("/{idadm}")
    public ResponseEntity<AdmModel> updateAdm(@PathVariable int idadm,
                                              @RequestBody @Valid AdmRecordDto admRecordDto) {
        Optional<AdmModel> admOptional = admRepository.findByidadm(idadm);
        if (admOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        AdmModel admModel = admOptional.get();
        BeanUtils.copyProperties(admRecordDto, admModel);
        AdmModel updatedAdm = admRepository.save(admModel);
        return ResponseEntity.ok(updatedAdm);
    }
}
