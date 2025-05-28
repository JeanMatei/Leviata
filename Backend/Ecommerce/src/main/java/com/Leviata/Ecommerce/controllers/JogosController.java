package com.Leviata.Ecommerce.controllers;

import com.Leviata.Ecommerce.dto.JogosRecordDto;
import com.Leviata.Ecommerce.model.JogosModel;
import com.Leviata.Ecommerce.repositories.JogosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/jogos")
public class JogosController {

    @Autowired
    private JogosRepository jogosRepository;

    @PostMapping
    public ResponseEntity<Object> saveJogo(@RequestBody @Valid JogosRecordDto jogosRecordDto) {
        Optional<JogosModel> jogoExist = jogosRepository.findByTitulo(jogosRecordDto.titulo());

        if (jogoExist.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Jogo com este título já existe.");
        }

        JogosModel jogosModel = new JogosModel();
        BeanUtils.copyProperties(jogosRecordDto, jogosModel);
        JogosModel savedJogo = jogosRepository.save(jogosModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedJogo);
    }

    @GetMapping
    public ResponseEntity<Iterable<JogosModel>> getAllJogos() {
        return ResponseEntity.ok(jogosRepository.findAll());
    }

    @GetMapping("/{idjogo}")
    public ResponseEntity<Object> getOneJogo(@PathVariable int idjogo) {
        Optional<JogosModel> jogo = jogosRepository.findAllByIdjogo(idjogo);

        if (jogo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogo não encontrado.");
        }
        return ResponseEntity.ok(jogo.get());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Object> getOneJogoByTitulo(@PathVariable String titulo) {
        Optional<JogosModel> jogo = jogosRepository.findByTitulo(titulo);
        if (jogo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogo não encontrado.");
        }
        return ResponseEntity.ok(jogo.get());
    }

    @GetMapping("/preco/{preco}")
    public ResponseEntity<Object> getByPreco(@PathVariable Double preco) {
        Optional<JogosModel> jogo = jogosRepository.findByPreco(preco);
        if (jogo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogo não encontrado.");
        }
        return ResponseEntity.ok(jogo.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateJogo(@PathVariable int id, @RequestBody @Valid JogosRecordDto jogosRecordDto) {
        Optional<JogosModel> jogo = jogosRepository.findById(id);

        if (jogo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogo não encontrado.");
        }

        JogosModel jogoAtualizado = jogo.get();
        BeanUtils.copyProperties(jogosRecordDto, jogoAtualizado);
        jogosRepository.save(jogoAtualizado);

        return ResponseEntity.ok(jogoAtualizado);
    }
}
