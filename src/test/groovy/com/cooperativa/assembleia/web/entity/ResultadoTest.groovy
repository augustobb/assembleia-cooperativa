package com.cooperativa.assembleia.web.entity

import spock.lang.Specification

class ResultadoTest extends Specification {

    Resultado resultado

    void setup() {
        resultado = new Resultado()
    }

    def "em resultado com mais votos sim do que não, a resposta deve ser Sim"() {
        given:
        resultado.setVotosSim(1)
        resultado.setVotosNao(0)

        expect:
        resultado.getResposta() == "Sim"
    }

    def "em resultado com menos votos sim do que não, a resposta deve ser Não"() {
        given:
        resultado.setVotosSim(1)
        resultado.setVotosNao(2)

        expect:
        resultado.getResposta() == "Não"
    }

    def "em resultado com mais o mesmo número de votos sim e não, a resposta deve ser Empate"() {
        given:
        resultado.setVotosSim(0)
        resultado.setVotosNao(0)

        expect:
        resultado.getResposta() == "Empate"
    }

}
