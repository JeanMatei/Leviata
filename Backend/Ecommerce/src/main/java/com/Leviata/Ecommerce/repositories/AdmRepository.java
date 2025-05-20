package com.Leviata.Ecommerce.repositories;

import com.Leviata.Ecommerce.model.AdmModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdmRepository extends JpaRepository<AdmModel, Integer> {

    Optional<AdmModel> findById(int id);

    Optional<AdmModel> findByAdmName(String name);

    Optional<AdmModel> findByEmail(String email);

    Optional<AdmModel> findBySenha(String senha);

}
