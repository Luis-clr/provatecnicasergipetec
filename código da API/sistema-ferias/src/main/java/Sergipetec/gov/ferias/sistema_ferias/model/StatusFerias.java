package Sergipetec.gov.ferias.sistema_ferias.model;

import Sergipetec.gov.ferias.sistema_ferias.enums.StatusFeriasEnum;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "status_ferias")
@Data
public class StatusFerias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status_ferias")
    private Integer idStatusFerias;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_ferias", nullable = false)
    private StatusFeriasEnum status;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "ferias_id_ferias", nullable = false)
    private Ferias ferias;
}