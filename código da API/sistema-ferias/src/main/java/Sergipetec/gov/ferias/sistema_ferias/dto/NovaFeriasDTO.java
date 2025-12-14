package Sergipetec.gov.ferias.sistema_ferias.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class NovaFeriasDTO {
    @NotNull
    private Integer servidorId;
    @NotNull
    private LocalDate dataInicio;
    @NotNull
    private LocalDate dataFim;

}