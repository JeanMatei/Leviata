package com.Leviata.Ecommerce.controllers;


import com.Leviata.Ecommerce.model.AvaModel;
import com.Leviata.Ecommerce.repositories.AvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")


public class AvaController {

    @Autowired
    private AvaRepository avaRepository;

    @PostMapping
    public ResponseEntity<AvaModel>createAva(@RequestBody AvaModel avaModel){
        AvaModel savedAvamodel = avaRepository.save(avaModel);
        return new ResponseEntity<>(savedAvamodel, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<AvaModel>> getAva(){
        List<AvaModel> avaliacaoList = avaRepository.findAll();
        return new ResponseEntity<>(avaliacaoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")

}
