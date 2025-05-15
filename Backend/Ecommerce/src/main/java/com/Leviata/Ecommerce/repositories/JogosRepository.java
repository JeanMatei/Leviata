package Leviata.leviatan.repositories;

import Leviata.leviatan.model.JogosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JogosRepository extends JpaRepository<JogosModel, Integer> {

    Optional<JogosModel> findById(int id);

    Optional<JogosModel> findByTitulo(String titulo);

    Optional<JogosModel> findByPreco(double preco);

    Optional<JogosModel> findByAprovacao(String aprovacao);

    List<JogosModel> findAllJogos();

}
