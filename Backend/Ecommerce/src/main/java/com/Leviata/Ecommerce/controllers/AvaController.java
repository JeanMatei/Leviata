package com.Leviata.Ecommerce.controllers;

import com.Leviata.Ecommerce.dto.AdmRecordDto;
import com.Leviata.Ecommerce.dto.AvaRecordDto;
import com.Leviata.Ecommerce.model.AdmModel;
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
    public ResponseEntity<AvaModel> createAva(@RequestBody AvaRecordDto avaRecordDto) {
        AvaModel avaModel = new AvaModel();

        try {
            BeanUtils.copyProperties(avaRecordDto, avaModel);
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }

        Optional<AvaModel> avaExist = avaRepository.findById(avaModel.getId());

        try {
            if (avaExist.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(avaModel);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(avaModel);

    }
    @GetMapping
    public ResponseEntity<List<AvaModel>> getAllAva() {

        return ResponseEntity.status(HttpStatus.OK).body(avaRepository.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<AvaModel> getAvaById(@RequestParam int id) {
        Optional<AvaModel> avaExist = avaRepository.findById(id);

        try {
            if (avaExist.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(avaExist.get());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(avaExist.get());
    }

    @GetMapping
    public ResponseEntity<AvaModel> getAvaByemail(@RequestParam String email) {

        Optional<AvaModel> avaExist = avaRepository.findByEmail(email);

        try {
            if (avaExist.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(avaExist.get());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body(avaExist.get());
    }

    @GetMapping("/id")
    public ResponseEntity<AvaModel> updateAva(@PathVariable(value = "id") int id,
                                              @RequestBody @Valid AvaRecordDto avaRecordDto) {
        AvaModel avaModel = new AvaModel();
        Optional<AvaModel> avaExist = avaRepository.findById(avaModel.getId());

        if (avaExist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(avaModel);
        }

        try {
            if (avaExist.isPresent()) {
                BeanUtils.copyProperties(avaRecordDto, avaModel);
                avaModel.setId(avaExist.get().getId());
                return ResponseEntity.status(HttpStatus.OK).body(avaModel);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        var admModel = avaExist.get();
        BeanUtils.copyProperties(avaRecordDto, avaModel);
        return ResponseEntity.status(HttpStatus.OK).body(avaModel);
    }
}



