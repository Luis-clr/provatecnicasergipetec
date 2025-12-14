package Sergipetec.gov.ferias.sistema_ferias.model;

import Sergipetec.gov.ferias.sistema_ferias.enums.StatusPagamentoEnum;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Pagamentos")
@Data
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  idPagamentos;

    @Column(name = "Valor_bruto", precision = 10, scale = 2)
    private BigDecimal valorBruto;

    @Column(name = "Valor_liquido", precision = 10, scale = 2)
    private BigDecimal valorLiquido;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(name = "Data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusPagamentoEnum status;

    @OneToOne
    @JoinColumn(name = "Ferias_idFerias", nullable = false)
    private Ferias ferias;
}