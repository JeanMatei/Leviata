package com.Leviata.Ecommerce.repositories;

import com.Leviata.Ecommerce.model.AdmModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdmRepository extends JpaRepository<AdmModel, Integer> {

    Optional<AdmModel> findByidadm(int idadm);

    Optional<AdmModel> findAllByidadm(int idadm);

    Optional<AdmModel> findByNome(String nome);

    Optional<AdmModel> findByEmail(String email);

}
