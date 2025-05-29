package com.Leviata.Ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbCompra")
public class CompraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCompra;

    @ManyToOne
    @JoinColumn(name = "clienteId", referencedColumnName = "clienteId")
    private ClienteModel cliente;

    @ManyToOne
    @JoinColumn(name = "jogosId", referencedColumnName = "idjogo")
    private JogosModel jogo;

    @NotNull
    private LocalDateTime dt_Compra;

    @NotNull
    private Double vl_Compra;

    public CompraModel() {}

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public JogosModel getJogo() {
        return jogo;
    }

    public void setJogo(JogosModel jogo) {
        this.jogo = jogo;
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
