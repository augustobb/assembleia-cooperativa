package com.cooperativa.assembleia.api.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class PautaRequest implements Serializable {
    private static final long serialVersionUID = 5541617492602158952L;

    @NotEmpty(message = "Pergunta inválida")
    private String pergunta;

}
