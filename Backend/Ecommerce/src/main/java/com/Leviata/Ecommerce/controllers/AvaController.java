package com.Leviata.Ecommerce.controllers;

import com.Leviata.Ecommerce.model.AvaModel;
import com.Leviata.Ecommerce.repositories.AvaRepository;
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
    public ResponseEntity<AvaModel> createAva(@RequestBody AvaModel avaModel) {
        AvaModel savedAvaModel = avaRepository.save(avaModel);
        return new ResponseEntity<>(savedAvaModel, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AvaModel>> getAllAva() {
        List<AvaModel> avaliacaoList = avaRepository.findAll();
        return new ResponseEntity<>(avaliacaoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaModel> getAvaById(@PathVariable int id) {
        Optional<AvaModel> avaModel = avaRepository.findById(id);
        return avaModel.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaModel> updateAva(@PathVariable int id, @RequestBody AvaModel avaModel) {
        if (!avaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        avaModel.setId(id);
        AvaModel updatedAvaModel = avaRepository.save(avaModel);
        return new ResponseEntity<>(updatedAvaModel, HttpStatus.OK);
    }
}
