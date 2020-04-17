package com.cooperativa.assembleia.web.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Resposta {
    SIM("Sim"),
    NAO("Não");

    private final String value;

    public static Resposta fromValue(String value) {
        return Arrays.stream(Resposta.values())
                .filter(e -> e.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
