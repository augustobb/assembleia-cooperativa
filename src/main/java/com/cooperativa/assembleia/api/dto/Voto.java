package com.cooperativa.assembleia.api.dto;

import com.cooperativa.assembleia.web.annotation.RespostaValida;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
public class Voto implements Serializable {
    private static final long serialVersionUID = 4575633174135859884L;

    @NotEmpty(message = "Número de CPF de associado inválido")
    private String cpfAssociado;

    @RespostaValida
    private String resposta;
}
