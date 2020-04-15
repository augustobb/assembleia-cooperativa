package com.cooperativa.assembleia.api.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
public class Pauta implements Serializable {
    private static final long serialVersionUID = 5541617492602158952L;

    private Long id;

    @NotEmpty(message = "Pergunta inválida")
    private String pergunta;

    @Min(value = 10, message = "Duração mínima da Pauta é 10 segundos")
    private Long segundosDuracao;

}
