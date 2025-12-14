package Sergipetec.gov.ferias.sistema_ferias.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeriasDetalheDTO {
    private FeriasDTO periodo;
    private String statusAtual;
    private List<StatusHistoricoDTO> historicoStatus;
    private PagamentoDTO pagamento;
}