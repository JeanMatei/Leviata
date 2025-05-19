package com.Leviata.Ecommerce.controllers;


import com.Leviata.Ecommerce.model.AvaModel;
import com.Leviata.Ecommerce.repositories.AvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
