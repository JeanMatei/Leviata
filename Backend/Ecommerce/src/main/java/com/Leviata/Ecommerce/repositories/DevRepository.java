package com.Leviata.Ecommerce.repositories;

import com.Leviata.Ecommerce.model.DevModel;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface DevRepository extends JpaRepository<DevModel, Integer> {

    Optional<DevModel> findById(int id);

    @NotBlank
    Optional<DevModel> findByEmail(String email);

    @NotBlank
    Optional<DevModel> FindBynmEmpresa(String nm_Empresa);

    @NotBlank
    Optional<DevModel> findBynmResponsal(String nm_Responsal);
}
