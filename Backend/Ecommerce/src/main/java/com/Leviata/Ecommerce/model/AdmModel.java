package com.Leviata.Ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tbAdm")
public class AdmModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @NotBlank
    private int id;

    @NotBlank
    private String nome;


    private String email;

    @NotBlank
    private String senha;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
