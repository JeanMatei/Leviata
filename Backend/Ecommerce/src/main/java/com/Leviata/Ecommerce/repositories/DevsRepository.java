package com.Leviata.Ecommerce.repositories;

import com.Leviata.Ecommerce.model.DevsModel;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface DevsRepository extends JpaRepository<DevsModel, Integer> {

    Optional<DevsModel> findById(int id);

    @NotBlank
    Optional<DevsModel> findByEmail(String email);

    @NotBlank
    Optional<DevsModel> FindBynmEmpresa(String nm_Empresa);

    @NotBlank
    Optional<DevsModel> findBynmResponsal(String nm_Responsal);
}
