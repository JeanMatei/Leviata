package com.Leviata.Ecommerce.repositories;


import com.Leviata.Ecommerce.model.AvaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AvaRepository extends JpaRepository<AvaModel,Integer> {

     Optional<AvaModel> findByidCompra(int idCompra);


    Optional<AvaModel> findByNota(int nota);


    Optional<AvaModel> findByDtAvaliacaoAfter(LocalDateTime data);


    Optional<AvaModel> findById(int id);
}
