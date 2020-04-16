package com.cooperativa.assembleia.web.message;

import lombok.Getter;

@Getter
public enum MessageKey {

    ERRO_NAO_ESPERADO("erro.naoEsperado"),
    PARAMETROS_INVALIDOS("erro.parametrosInvalidos"),
    SESSAO_JA_ABERTA_PARA_PAUTA("erro.sessaoJaAbertaParaPauta");

    private final String key;

    private MessageKey(String key) {
        this.key = key;
    }
}
