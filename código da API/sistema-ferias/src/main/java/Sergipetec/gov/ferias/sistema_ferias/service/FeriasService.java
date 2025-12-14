package Sergipetec.gov.ferias.sistema_ferias.service;

import Sergipetec.gov.ferias.sistema_ferias.dto.*;
import Sergipetec.gov.ferias.sistema_ferias.enums.StatusFeriasEnum;
import Sergipetec.gov.ferias.sistema_ferias.model.Ferias;
import Sergipetec.gov.ferias.sistema_ferias.model.StatusFerias;
import Sergipetec.gov.ferias.sistema_ferias.repository.FeriasRepository;
import Sergipetec.gov.ferias.sistema_ferias.repository.ServidorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class FeriasService {

    private final FeriasRepository feriasRepository;
    private final ServidorRepository servidorRepository;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public FeriasDetalheDTO buscarDetalhes(Integer id) {
        var ferias = feriasRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new RuntimeException("Férias não encontradas"));

        var periodo = new FeriasDTO(
                ferias.getIdFerias(),
                ferias.getDataInicio().format(dateFormatter),
                ferias.getDataFim().format(dateFormatter),
                ferias.getQuantidadeDias(),
                ferias.getData_criacao().format(formatter)
        );

        var historico = ferias.getHistoricoStatus().stream()
                .map(h -> new StatusHistoricoDTO(
                        h.getStatus().name(),
                        h.getDataCriacao().format(formatter)
                ))
                .collect(Collectors.toList());

        PagamentoDTO pagamentoDTO = null;
        if (ferias.getPagamento() != null) {
            var pag = ferias.getPagamento();
            pagamentoDTO = new PagamentoDTO(
                    pag.getIdPagamentos(),
                    pag.getDataPagamento() != null ? pag.getDataPagamento().format(dateFormatter) : null,
                    pag.getValorBruto(),
                    pag.getValorLiquido(),
                    pag.getStatus().name()
            );
        }

        return new FeriasDetalheDTO(periodo, ferias.getStatus().name(), historico, pagamentoDTO);
    }

    @Transactional
    public Integer criarNovaFerias(NovaFeriasDTO dto) {

        var servidor = servidorRepository.findById(dto.getServidorId())
                .orElseThrow(() -> new RuntimeException("Servidor não encontrado no banco de dados"));

        var ferias = new Ferias();
        ferias.setServidor(servidor);
        ferias.setDataInicio(dto.getDataInicio());
        ferias.setDataFim(dto.getDataFim());
        ferias.setStatus(StatusFeriasEnum.Solicitada);
        ferias.setData_criacao(LocalDateTime.now());

        String mes = dto.getDataInicio()
                .getMonth()
                .getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));

        ferias.setFeriasNome("Férias " +
                mes.substring(0, 1).toUpperCase() + mes.substring(1));

        int dias = (int) ChronoUnit.DAYS.between(
                dto.getDataInicio(),
                dto.getDataFim()
        ) + 1;

        ferias.setQuantidadeDias(dias);

        ferias = feriasRepository.save(ferias);

        var statusInicial = new StatusFerias();
        statusInicial.setFerias(ferias);
        statusInicial.setStatus(StatusFeriasEnum.Solicitada);
        statusInicial.setDataCriacao(LocalDateTime.now());

        ferias.getHistoricoStatus().add(statusInicial);

        feriasRepository.save(ferias);

        return ferias.getIdFerias();
    }
}