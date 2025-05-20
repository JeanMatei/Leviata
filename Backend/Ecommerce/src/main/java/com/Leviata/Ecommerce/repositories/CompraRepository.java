package com.Leviata.Ecommerce.repositories;

import com.Leviata.Ecommerce.model.CompraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompraRepository extends JpaRepository<CompraModel, Integer> {

    Optional<CompraModel>findByCliente_id(int id);

    Optional<CompraModel>findByJogo_id(int id);

    Optional<CompraModel> findByForma_pagamento(CompraModel.FormaPagamento formaPagamento);

    Optional<CompraModel>findByData_compraAfter(LocalDateTime dataCompra);


    Optional<CompraModel> findById(int id);
}
