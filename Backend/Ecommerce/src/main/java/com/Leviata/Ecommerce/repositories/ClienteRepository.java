package com.Leviata.Ecommerce.repositories;

import ch.qos.logback.core.net.server.Client;
import com.Leviata.Ecommerce.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteModel, Integer> {

    Optional<ClienteModel> findByNome(String nome);

    Optional<ClienteModel> findByEmail(String email);

    Optional<ClienteModel> findByTelefone(String telefone);


}
