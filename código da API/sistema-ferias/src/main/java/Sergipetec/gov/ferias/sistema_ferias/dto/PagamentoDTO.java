package Sergipetec.gov.ferias.sistema_ferias.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDTO {
    private Integer id;
    private String dataPagamento;
    private BigDecimal valorBruto;
    private BigDecimal valorLiquido;
    private String status;
}
