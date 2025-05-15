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

    // Salvar Jogo
    @PostMapping
    public ResponseEntity<JogosModel> saveJogo(@RequestBody @Valid JogosRecordDto jogosRecordDto) {
        // Criar um novo modelo para salvar
        JogosModel jogosModel = new JogosModel();
        try {
            // Copiar as propriedades do DTO para o modelo
            BeanUtils.copyProperties(jogosRecordDto, jogosModel);
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }

        // Verificar se o jogo já existe
        Optional<JogosModel> jogoExistente = jogosRepository.findById(Integer.parseInt(jogosRecordDto.titulo()));
        if (jogoExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(jogoExistente.get());
        }

        // Salvar o jogo no banco
        JogosModel jogosModel1 = jogosRepository.save(jogosModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(jogosModel1);
    }

    // Listar todos os jogos
    @GetMapping
    public ResponseEntity<List<JogosModel>> getAllJogos() {
        return ResponseEntity.status(HttpStatus.OK).body(jogosRepository.findAll());
    }

    // Buscar um jogo por ID
    @GetMapping("/{id}")
    public ResponseEntity<JogosModel> getOneJogo(@PathVariable(value = "id") int id) {
        Optional<JogosModel> jogo = jogosRepository.findById(id);
        return jogo.map(j -> ResponseEntity.status(HttpStatus.OK).body(j))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Buscar um jogo por Título
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<JogosModel> getOneJogoByTitulo(@PathVariable(value = "titulo") String titulo) {
        Optional<JogosModel> jogo = jogosRepository.findByTitulo(titulo);
        return jogo.map(j -> ResponseEntity.status(HttpStatus.OK).body(j))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Buscar um jogo por Preço
    @GetMapping("/preco/{preco}")
    public ResponseEntity<JogosModel> getByPreco(@PathVariable(value = "preco") Double preco) {
        Optional<JogosModel> jogo = jogosRepository.findByPreco(preco);
        return jogo.map(j -> ResponseEntity.status(HttpStatus.OK).body(j))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Atualizar um jogo
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
