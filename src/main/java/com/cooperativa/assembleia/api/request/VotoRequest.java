package com.cooperativa.assembleia.api.request;

import com.cooperativa.assembleia.web.annotation.RespostaValida;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class VotoRequest implements Serializable {
    private static final long serialVersionUID = 4575633174135859884L;

    @NotNull(message = "Identificador de associado inválido")
    private Long associadoId;

    @RespostaValida
    private String resposta;
}
