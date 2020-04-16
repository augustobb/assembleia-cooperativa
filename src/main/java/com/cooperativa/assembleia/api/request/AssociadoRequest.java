package com.cooperativa.assembleia.api.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class AssociadoRequest implements Serializable {
    private static final long serialVersionUID = -8256067871815002918L;

    @NotEmpty(message = "Número de CPF de associado inválido")
    private String cpf;
}
