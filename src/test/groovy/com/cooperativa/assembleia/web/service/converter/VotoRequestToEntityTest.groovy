package com.cooperativa.assembleia.web.service.converter

import com.cooperativa.assembleia.api.request.VotoRequest
import com.cooperativa.assembleia.web.entity.Associado
import com.cooperativa.assembleia.web.service.AssociadoService
import spock.lang.Specification

import static com.cooperativa.assembleia.web.enums.Resposta.NAO
import static com.cooperativa.assembleia.web.enums.Resposta.SIM

class VotoRequestToEntityTest extends Specification {

    VotoRequestToEntity votoRequestToEntity

    def associadoService = Mock(AssociadoService)

    def associado = Mock(Associado)

    void setup() {
        associadoService.buscarComDetalhesHabilitacaoVoto(_) >> associado
        votoRequestToEntity = new VotoRequestToEntity(associadoService)
    }

    def "deve converter request com resposta Sim corretamente para entity"() {
        given:
        def request = VotoRequest.builder().associadoId(1L).resposta("Sim").build()

        when:
        def entity = votoRequestToEntity.apply(request)

        then:
        !entity.getId()
        entity.getAssociado() == associado
        entity.getResposta() == SIM
    }

    def "deve converter request com resposta Não corretamente para entity"() {
        given:
        def request = VotoRequest.builder().associadoId(1L).resposta("Não").build()

        when:
        def entity = votoRequestToEntity.apply(request)

        then:
        !entity.getId()
        entity.getAssociado() == associado
        entity.getResposta() == NAO
    }

}
