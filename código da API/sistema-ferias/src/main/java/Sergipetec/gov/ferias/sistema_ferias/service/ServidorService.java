package Sergipetec.gov.ferias.sistema_ferias.service;

import Sergipetec.gov.ferias.sistema_ferias.dto.FeriasListaDTO;
import Sergipetec.gov.ferias.sistema_ferias.model.Servidor;
import Sergipetec.gov.ferias.sistema_ferias.repository.FeriasRepository;
import Sergipetec.gov.ferias.sistema_ferias.repository.ServidorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServidorService {

    private final ServidorRepository servidorRepository;
    private final FeriasRepository feriasRepository;

    public Servidor buscarPorId(Integer id) {
        return servidorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servidor não encontrado"));
    }

    public List<FeriasListaDTO> listarFeriasDoServidor(Integer servidorId) {
        var ferias = feriasRepository.findByServidorIdServidorOrderByDataInicioDesc(servidorId);

        return ferias.stream().map(f -> {
            BigDecimal valor = f.getPagamento() != null ?
                    f.getPagamento().getValorLiquido() : BigDecimal.ZERO;

            return new FeriasListaDTO(
                    f.getIdFerias(),
                    null, // anoExercicio não existe mais
                    f.getDataInicio(),
                    f.getDataFim(),
                    f.getQuantidadeDias(),
                    f.getStatus().name(),
                    valor
            );
        }).collect(Collectors.toList());
    }
}