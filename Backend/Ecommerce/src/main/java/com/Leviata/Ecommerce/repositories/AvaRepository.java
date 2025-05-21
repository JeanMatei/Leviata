package com.Leviata.Ecommerce.repositories;


import com.Leviata.Ecommerce.model.AvaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvaRepository extends JpaRepository<AvaModel,Integer> {

     Optional<AvaModel> findByCompraId(int compraId);


    Optional<AvaModel> findByNota(int nota);


    Optional<AvaModel> findByDataAvaliacaoAfter(LocalDateTime data);

    Optional<AvaModel> findByEmail(String email);


    Optional<AvaModel> findById(int id);
}
