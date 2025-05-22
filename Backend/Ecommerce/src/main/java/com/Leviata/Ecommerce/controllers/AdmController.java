package com.Leviata.Ecommerce.controllers;

import com.Leviata.Ecommerce.dto.AdmRecordDto;
import com.Leviata.Ecommerce.model.AdmModel;
import com.Leviata.Ecommerce.repositories.AdmRepository;
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
@RequestMapping("/adm")
public class AdmController {

    @Autowired
    private AdmRepository admRepository;

    @PostMapping
    public ResponseEntity<AdmModel> createAdm(@RequestBody @Valid AdmRecordDto admRecordDto) {

        AdmModel admModel = new AdmModel();

        try {
            BeanUtils.copyProperties(admRecordDto, admModel);
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }

        Optional<AdmModel> admExist = admRepository.findById(admModel.getIdadm());

        try {
            if (admExist.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(admModel);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(admModel);
    }

    @GetMapping
    public ResponseEntity<Iterable<AdmModel>> getAllAdm() {

        return ResponseEntity.status(HttpStatus.OK).body(admRepository.findAll());
    }

    @GetMapping("/idadm")
    public ResponseEntity<AdmModel> getAdmById(@RequestParam int idadm) {
        Optional<AdmModel> admExist = admRepository.findAllByidadm(idadm);

        try {
            if (admExist.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(admExist.get());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(admExist.get());
    }

    @GetMapping("/email")
    public ResponseEntity<AdmModel> getAdmByemail(@RequestParam String email) {

        Optional<AdmModel> admExist = admRepository.findByEmail(email);

        try {
            if (admExist.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(admExist.get());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body(admExist.get());
    }

    @GetMapping("/id")
    public ResponseEntity<AdmModel> updateAdm(@PathVariable(value = "idadm") int id,
                                              @RequestBody @Valid AdmRecordDto admRecordDto) {
        AdmModel admModel = new AdmModel();
        Optional<AdmModel> admExist = admRepository.findById(admModel.getIdadm());

        if (admExist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(admModel);
        }

        try {
            if (admExist.isPresent()) {
                BeanUtils.copyProperties(admRecordDto, admModel);
                admModel.setIdadm(admExist.get().getIdadm());
                return ResponseEntity.status(HttpStatus.OK).body(admModel);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        var admModel1 = admExist.get();
        BeanUtils.copyProperties(admRecordDto, admModel);
        return ResponseEntity.status(HttpStatus.OK).body(admModel);
    }
}
