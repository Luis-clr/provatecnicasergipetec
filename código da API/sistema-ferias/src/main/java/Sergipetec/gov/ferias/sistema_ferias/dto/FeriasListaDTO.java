package Sergipetec.gov.ferias.sistema_ferias.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeriasListaDTO {
    private Integer id;
    private Integer anoExercicio;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Integer quantidadeDias;
    private String statusAtual;
    private BigDecimal valorPagamento;
}