package Sergipetec.gov.ferias.sistema_ferias.model;

import Sergipetec.gov.ferias.sistema_ferias.enums.StatusFeriasEnum;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Ferias")
@Data
public class Ferias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  idFerias;

    @Column(name = "ferias_nome", nullable = false)
    private String feriasNome;

    @Column(name = "Data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "Data_fim", nullable = false)
    private LocalDate dataFim;

    @Column(name = "Quantidade_de_dias", nullable = false)
    private Integer quantidadeDias;

    @Column(name = "Data_criacao", nullable = false)
    private LocalDateTime Data_criacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false, columnDefinition = "status_ferias_enum")
    private StatusFeriasEnum status;

    @ManyToOne
    @JoinColumn(name = "Servidor_idServidor", nullable = false)
    private Servidor servidor;

    @OneToMany(mappedBy = "ferias", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StatusFerias> historicoStatus = new ArrayList<>();

    @OneToOne(mappedBy = "ferias", cascade = CascadeType.ALL)
    private Pagamento pagamento;
}