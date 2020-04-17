package com.cooperativa.assembleia.web.entity

import com.cooperativa.assembleia.web.exception.BusinessException
import spock.lang.Specification

import static com.cooperativa.assembleia.web.message.MessageKey.ASSOCIADO_NAO_PODE_VOTAR

class AssociadoTest extends Specification {

    Associado associado

    void setup() {
        associado = new Associado()
    }

    def "quando associado não pode votar, ao validar habilitação de voto, deve lançar erro de negócio"() {
        given: "associado que não pode votar"
        associado.setPodeVotar(false)

        when:
        associado.validarHabilitacaoVoto()

        then: "lançar erro de negócio"
        BusinessException ex = thrown()
        ex.getMessageKey() == ASSOCIADO_NAO_PODE_VOTAR
    }

    def "quando associado pode votar, ao validar habilitação de voto, não deve lançar erro de negócio"() {
        given: "associado que pode votar"
        associado.setPodeVotar(true)

        expect: "não lançar erro de negócio"
        associado.validarHabilitacaoVoto()

    }
}
