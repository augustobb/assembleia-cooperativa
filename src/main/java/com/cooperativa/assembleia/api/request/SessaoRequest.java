package com.cooperativa.assembleia.api.request;

import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
public class SessaoRequest implements Serializable {
    private static final long serialVersionUID = 2070097508917542297L;

    @Min(value = 10, message = "Duração mínima da Pauta é 10 segundos")
    private Long segundosDuracao;
}
