package com.cooperativa.assembleia.api.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class PautaResponse implements Serializable {
    private static final long serialVersionUID = 7948169024506305171L;

    private Long id;
    private String pergunta;
}