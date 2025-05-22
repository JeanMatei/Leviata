package com.Leviata.Ecommerce.controllers;

import com.Leviata.Ecommerce.dto.JogosRecordDto;
import com.Leviata.Ecommerce.model.JogosModel;
import com.Leviata.Ecommerce.repositories.JogosRepository;
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
@CrossOrigin(origins = "*")
@RequestMapping("/jogos")
public class JogosController {

    @Autowired
    private JogosRepository jogosRepository;


    @PostMapping
    public ResponseEntity<JogosModel> saveJogo(@RequestBody @Valid JogosRecordDto jogosRecordDto) {

        JogosModel jogosModel = new JogosModel();

        try {
            BeanUtils.copyProperties(jogosRecordDto, jogosModel);
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }


        Optional<JogosModel> jogoExist = jogosRepository.findById(Integer.parseInt(jogosRecordDto.titulo()));

        try {
            if (jogoExist.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(jogoExist.get());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JogosModel jogosModel1 = jogosRepository.save(jogosModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(jogosModel1);
    }


    @GetMapping
    public ResponseEntity<Iterable<JogosModel>> getAllJogos() {
        return ResponseEntity.status(HttpStatus.OK).body(jogosRepository.findAll());
    }


    @GetMapping("/{idjogo}")
    public ResponseEntity<JogosModel> getOneJogo(@PathVariable(value = "idjogo") int idjogo) {
        Optional<JogosModel> jog = jogosRepository.findAllByIdjogo(idjogo);

        if (jog.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(jog.get());

    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<JogosModel> getOneJogoByTitulo(@PathVariable(value = "titulo") String titulo) {
        Optional<JogosModel> jogo = jogosRepository.findByTitulo(titulo);
        return jogo.map(j -> ResponseEntity.status(HttpStatus.OK).body(j))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/preco/{preco}")
    public ResponseEntity<JogosModel> getByPreco(@PathVariable(value = "preco") Double preco) {
        Optional<JogosModel> jogo = jogosRepository.findByPreco(preco);
        return jogo.map(j -> ResponseEntity.status(HttpStatus.OK).body(j))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateJogo(@PathVariable(value = "id") int id,
                                             @RequestBody @Valid JogosRecordDto jogosRecordDto) {
        Optional<JogosModel> jogo = jogosRepository.findById(id);

        if (jogo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Jogo não encontrado");
        }

        JogosModel jogoAtualizado = jogo.get();
        try {
            BeanUtils.copyProperties(jogosRecordDto, jogoAtualizado);
            jogosRepository.save(jogoAtualizado);
            return ResponseEntity.status(HttpStatus.OK).body(jogoAtualizado);
        } catch (BeansException e) {
            throw new RuntimeException("Erro ao atualizar jogo", e);
        }

    }
}
