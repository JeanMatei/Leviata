package com.Leviata.Ecommerce.controllers;
import Leviata.leviatan.dto.JogosRecordDto;
import Leviata.leviatan.model.JogosModel;
import Leviata.leviatan.repositories.JogosRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.aspectj.apache.bcel.util.Repository;
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
    public ResponseEntity<JogosModel> saveJogo(@RequestBody @Valid JogosRecordDto jogosRecordDto, JogosModel jogosModel) {
        var jogosModel1 = jogosRepository.save(jogosModel);

        try {
            BeanUtils.copyProperties(jogosRecordDto, jogosModel);
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }

        Optional<JogosModel> jogoExitstente = jogosRepository.findById(Integer.parseInt(jogosRecordDto.titulo()));

        if (jogoExitstente.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(jogoExitstente.get());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(null);

    }

    @GetMapping
    public ResponseEntity<List<JogosModel>> getAllJogos() {
        return ResponseEntity.status(HttpStatus.OK).body(jogosRepository.findAllJogos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogosModel> getOneJogo(@PathVariable(value = "id") int id) {
        Optional<JogosModel> jogo = jogosRepository.findById(id);
        try {
            if (jogo.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(jogo.get());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> UpdateJogo(@PathVariable(value = "id") int id,
                                             @RequestBody @Valid JogosRecordDto jogosRecordDto) {

        Optional<JogosModel> jogo = jogosRepository.findById(id);
        if (jogo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Jogo não encontrado");

        }

        Optional<JogosModel> jogoUpdate = jogosRepository.findById(id);
        if (jogoUpdate.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(jogoUpdate.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
//@GetMapping("/{id}")
//public ResponseEntity<Object> updateJogo(@PathVariable(value = "id") int id,
//                                          @RequestBody @Valid JogosRecordDto jogosRecordDto) {
//
//    // Buscar o jogo no banco de dados
//    Optional<JogosModel> jogoOpt = jogosRepository.findById(id);
//
//    // Verifica se o jogo existe
//    if (jogoOpt.isEmpty()) {
//        // Se o jogo não existir, retorna 404 (Not Found)
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                             .body("Jogo não encontrado");
//    }
//
//    // Jogo encontrado
//    JogosModel jogoExistente = jogoOpt.get();
//
//    // Atualiza os campos do jogo com os dados do jogosRecordDto
//    jogoExistente.setTitulo(jogosRecordDto.titulo());
//    jogoExistente.setPreco(jogosRecordDto.preco());
//    jogoExistente.setAprovado(jogosRecordDto.aprovado());
//    jogoExistente.setTelefoneFuncionario(jogosRecordDto.telefoneFuncionario());
//    jogoExistente.setCpfFuncionario(jogosRecordDto.cpfFuncionario());
//
//    // Salva as alterações no banco de dados
//    jogosRepository.save(jogoExistente);
//
//    // Retorna a resposta 200 OK, com o jogo atualizado
//    return ResponseEntity.status(HttpStatus.OK).body(jogoExistente);
//}