package com.cooperativa.assembleia.web.message;

import lombok.Getter;

@Getter
public enum MessageKey {

    ERRO_NAO_ESPERADO("erro.naoEsperado"),
    PARAMETROS_INVALIDOS("erro.parametrosInvalidos"),
    SESSAO_JA_ABERTA_PARA_PAUTA("erro.sessaoJaAbertaParaPauta"),
    PAUTA_SEM_SESSAO("erro.pautaSemSessao"),
    SESSAO_PAUTA_ENCERRADA("erro.sessaoPautaEncerrada"),
    CPF_ASSOCIADO_INVALIDO("erro.cpfAssociadoInvalido"),
    ASSOCIADO_NAO_PODE_VOTAR("erro.associadoNaoPodeVotar"),
    ASSOCIADO_JA_VOTOU_NESTA_PAUTA("erro.associadoJVotouNestaPauta");

    private final String key;

    private MessageKey(String key) {
        this.key = key;
    }
}
