package com.Leviata.Ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tbAdm")
public class AdmModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idadm;

    @NotBlank
    private String nome;

    @NotBlank
    private String nm_empresa;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    private String whatsapp;

    public AdmModel(int idadm) {
        this.idadm = idadm;
    }

    public AdmModel() {

    }

    public int getIdadm() {
        return idadm;
    }

    public void setIdadm(int idadm) {
        this.idadm = idadm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNm_empresa() {
        return nm_empresa;
    }

    public void setNm_empresa(String nm_empresa) {
        this.nm_empresa = nm_empresa;
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

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }
}
