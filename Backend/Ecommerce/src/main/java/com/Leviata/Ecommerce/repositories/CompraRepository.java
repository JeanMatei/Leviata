package com.Leviata.Ecommerce.repositories;

import com.Leviata.Ecommerce.model.CompraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompraRepository extends JpaRepository<CompraModel, Integer> {

    Optional<CompraModel> findById(int id);

    Optional<CompraModel> findByCliente_ClienteId(int clienteId);

    Optional<CompraModel> findByJogo_Idjogo(int jogoId);
}
