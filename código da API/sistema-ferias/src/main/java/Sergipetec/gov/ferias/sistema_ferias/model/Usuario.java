package Sergipetec.gov.ferias.sistema_ferias.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "login", nullable = false, unique = true, length = 50)
    private String login;

    @Column(name = "senha", nullable = false, length = 255)
    private String senha;

    @OneToOne
    @JoinColumn(name = "funcionario_servidor_id_servidor", nullable = false)
    private Servidor servidor;
}