package com.cooperativa.assembleia.web.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Resposta {
    SIM("Sim"),
    NAO("Não");

    private final String value;
}
