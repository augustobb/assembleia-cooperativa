package com.cooperativa.assembleia.api.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Resultado implements Serializable {
    private static final long serialVersionUID = 255977104833157800L;

    private Pauta pauta;
    private String resposta;
    private Long votosSim;
    private Long votosNao;

}
