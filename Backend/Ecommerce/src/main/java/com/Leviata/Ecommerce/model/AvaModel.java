package com.Leviata.Ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbAva")
public class AvaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private int idCompra;

    @NotNull
    private Integer nota;

    private String comentario;

    @NotNull
    private LocalDateTime dtAvaliacao;

    public AvaModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getDtAvaliacao() {
        return dtAvaliacao;
    }

    public void setDtAvaliacao(LocalDateTime dtAvaliacao) {
        this.dtAvaliacao = dtAvaliacao;
    }
}
