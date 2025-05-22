package com.Leviata.Ecommerce.repositories;

import com.Leviata.Ecommerce.model.JogosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JogosRepository extends JpaRepository<JogosModel, Integer> {

    Optional<JogosModel> findById(int id);

    Optional<JogosModel> findByTitulo(String titulo);

    Optional<JogosModel> findByPreco(double preco);

    Optional<JogosModel> findAllByIdjogo(int idjogo);

}
