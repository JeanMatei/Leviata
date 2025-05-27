package com.Leviata.Ecommerce.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbCompra")
public class CompraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCompra;

    @NotNull
    private int clienteId;

    @NotNull
    private int jogosId;

    @NotNull
    private LocalDateTime dt_Compra;

    @NotNull
    private Double vl_Compra;

    public CompraModel() {
    }

    public enum FormaPagamento {
        CARTAO,
        EPIX
    }
    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getJogosId() {
        return jogosId;
    }

    public void setJogosId(int jogosId) {
        this.jogosId = jogosId;
    }

    public LocalDateTime getDt_Compra() {
        return dt_Compra;
    }

    public void setDt_Compra(LocalDateTime dt_Compra) {
        this.dt_Compra = dt_Compra;
    }

    public Double getVl_Compra() {
        return vl_Compra;
    }

    public void setVl_Compra(Double vl_Compra) {
        this.vl_Compra = vl_Compra;
    }
}