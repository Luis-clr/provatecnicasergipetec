package Sergipetec.gov.ferias.sistema_ferias.controller;

import Sergipetec.gov.ferias.sistema_ferias.dto.FeriasDetalheDTO;
import Sergipetec.gov.ferias.sistema_ferias.dto.NovaFeriasDTO;
import Sergipetec.gov.ferias.sistema_ferias.service.FeriasService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/ferias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FeriasController {

    private final FeriasService feriasService;

    /**
     * GET /api/ferias/{id}
     * Retorna detalhes completos de um período de férias
     *     "periodo": {
     *         "id": 1,
     *         "dataInicio": "02/01/2025",
     *         "dataFim": "31/01/2025",
     *         "quantidadeDias": 30,
     *         "Data_criacao": "13/12/2025 17:54"
     *     },
     *     "statusAtual": "Aprovada",
     *     "historicoStatus": [
     *         {
     *             "status": "Solicitada",
     *             "data": "13/12/2025 17:54"
     *         },
     *         {
     *             "status": "Aprovada",
     *             "data": "13/12/2025 17:54"
     *         }
     *     ],
     *     "pagamento": {
     *         "id": 1,
     *         "dataPagamento": "05/01/2025",
     *         "valorBruto": 5000.00,
     *         "valorLiquido": 4200.00,
     *         "status": "Pago"
     *     }
     */
    @GetMapping("/{id}")
    public ResponseEntity buscarDetalhes(@PathVariable Integer id) {
        var detalhes = feriasService.buscarDetalhes(id);
        return ResponseEntity.ok(detalhes);
    }

    /**
     * POST /api/ferias
     * Cria uma nova solicitação de férias
     * {
     *     "message": "Solicitação de férias criada com sucesso",
     *     "id": 7
     * }
     */
    @PostMapping
    public ResponseEntity<Map> criarNovaFerias(@Valid @RequestBody NovaFeriasDTO dto) {
        Integer id = feriasService.criarNovaFerias(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "id", id,
                "message", "Solicitação de férias criada com sucesso"
        ));
    }
}