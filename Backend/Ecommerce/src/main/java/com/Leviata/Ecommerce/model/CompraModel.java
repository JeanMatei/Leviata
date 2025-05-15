package Leviata.leviatan.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbCompra")
public class CompraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int cliente_id;

    private int jogo_id;

    private LocalDateTime data_compra;

    private BigDecimal preco_pago;

    @Enumerated(EnumType.STRING)
    private FormaPagamento forma_pagamento;


    public enum FormaPagamento {
        CARTAO,
        EPIX
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getJogo_id() {
        return jogo_id;
    }

    public void setJogo_id(int jogo_id) {
        this.jogo_id = jogo_id;
    }

    public LocalDateTime getData_compra() {
        return data_compra;
    }

    public void setData_compra(LocalDateTime data_compra) {
        this.data_compra = data_compra;
    }

    public BigDecimal getPreco_pago() {
        return preco_pago;
    }

    public void setPreco_pago(BigDecimal preco_pago) {
        this.preco_pago = preco_pago;
    }

    public FormaPagamento getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(FormaPagamento forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }
}
