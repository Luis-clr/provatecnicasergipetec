package Sergipetec.gov.ferias.sistema_ferias.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Funcionario_servidor")
@Data
public class Servidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  idServidor;

    @Column(name = "Matricula", nullable = false, unique = true, length = 20)
    private Integer matricula;

    @Column(name = "Funcionario_nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "Email", length = 100)
    private String email;

    @Column(name = "Orgao", length = 200)
    private String orgao;

    @Column(name = "Data_criacao")
    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "servidor", cascade = CascadeType.ALL)
    private List<Ferias> ferias = new ArrayList<>();

    @OneToOne(mappedBy = "servidor", cascade = CascadeType.ALL)
    private Usuario usuario;
}