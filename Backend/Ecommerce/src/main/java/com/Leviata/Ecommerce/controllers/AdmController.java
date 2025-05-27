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
        return admRepository.findById(idadm)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/email")
    public ResponseEntity<AdmModel> getAdmByEmail(@RequestParam String email) {
        return admRepository.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PutMapping("/{idadm}")
    public ResponseEntity<AdmModel> updateAdm(@PathVariable int idadm,
                                              @RequestBody @Valid AdmRecordDto admRecordDto) {
        Optional<AdmModel> admOptional = admRepository.findById(idadm);
        if (admOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        AdmModel admModel = admOptional.get();
        BeanUtils.copyProperties(admRecordDto, admModel);
        AdmModel updatedAdm = admRepository.save(admModel);
        return ResponseEntity.ok(updatedAdm);
    }

    @DeleteMapping("/{idadm}")
    public ResponseEntity<Void> deleteAdm(@PathVariable int idadm) {
        if (!admRepository.existsById(idadm)) {
            return ResponseEntity.notFound().build();
        }
        admRepository.deleteById(idadm);
        return ResponseEntity.noContent().build();
    }
}
