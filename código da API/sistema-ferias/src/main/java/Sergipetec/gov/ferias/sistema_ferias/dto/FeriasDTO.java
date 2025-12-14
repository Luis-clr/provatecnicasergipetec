package Sergipetec.gov.ferias.sistema_ferias.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeriasDTO {
    private Integer id;
    private String dataInicio;
    private String dataFim;
    private Integer quantidadeDias;
    private String Data_criacao;
}
