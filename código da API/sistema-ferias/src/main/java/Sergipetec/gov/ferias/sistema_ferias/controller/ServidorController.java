package Sergipetec.gov.ferias.sistema_ferias.controller;

import Sergipetec.gov.ferias.sistema_ferias.dto.FeriasListaDTO;
import Sergipetec.gov.ferias.sistema_ferias.service.ServidorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/servidores")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ServidorController {

    private final ServidorService servidorService;

    /**
     * GET /api/servidores/{id}/ferias
     * Lista todos os períodos de férias de um servidor
     *  Retorno da api
     *     {
     *         "id": 1,
     *         "anoExercicio": null,
     *         "dataInicio": "2025-01-02",
     *         "dataFim": "2025-01-31",
     *         "quantidadeDias": 30,
     *         "statusAtual": "Aprovada",
     *         "valorPagamento": 4200.00
     *     }
     */
    @GetMapping("/{id}/ferias")
    public ResponseEntity<List> listarFeriasDoServidor(@PathVariable Integer id) {
        var ferias = servidorService.listarFeriasDoServidor(id);
        return ResponseEntity.ok(ferias);
    }
}