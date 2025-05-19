package com.Leviata.Ecommerce.repositories;


import com.Leviata.Ecommerce.model.AvaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvaRepository extends JpaRepository<AvaModel,Integer> {

    List<AvaModel> findByCompraId( int compraId);


    List<AvaModel> findByNota(int nota);


    List<AvaModel> findByDataAvaliacaoAfter(LocalDateTime data);


    Optional<AvaModel> findById(int id);
}
