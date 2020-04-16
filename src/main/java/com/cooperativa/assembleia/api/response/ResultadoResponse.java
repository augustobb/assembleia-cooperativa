package com.cooperativa.assembleia.api.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ResultadoResponse implements Serializable {
    private static final long serialVersionUID = 255977104833157800L;

    private PautaResponse pauta;
    private String resposta;
    private Long votosSim;
    private Long votosNao;

}
