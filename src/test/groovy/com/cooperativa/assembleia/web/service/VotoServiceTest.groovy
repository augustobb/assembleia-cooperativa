package com.cooperativa.assembleia.web.service

import com.cooperativa.assembleia.api.request.VotoRequest
import com.cooperativa.assembleia.web.entity.Associado
import com.cooperativa.assembleia.web.entity.Pauta
import com.cooperativa.assembleia.web.entity.Voto
import com.cooperativa.assembleia.web.exception.BusinessException
import com.cooperativa.assembleia.web.repository.VotoRepository
import com.cooperativa.assembleia.web.service.converter.VotoRequestToEntity
import spock.lang.Specification

import static com.cooperativa.assembleia.web.message.MessageKey.ASSOCIADO_JA_VOTOU_NESTA_PAUTA

class VotoServiceTest extends Specification {

    VotoService service

    def repository = Mock(VotoRepository)
    def toEntity = Mock(VotoRequestToEntity)

    def mockRequest = Mock(VotoRequest)
    def mockVoto = Mock(Voto)
    def mockPauta = Mock(Pauta)
    def mockAssociado = Mock(Associado)

    void setup() {
        mockVoto.getAssociado() >> mockAssociado
        toEntity.apply(mockRequest) >> mockVoto
        service = new VotoService(repository, toEntity)
    }

    def "quando usuário que ainda não votou na pauta votar nela, deve salvar voto" () {
        Voto votoSalvo = null

        given: "voto de usuário que ainda não votou na pauta"
        repository.findByPautaAndAssociado(mockVoto.getPauta(), mockAssociado) >> Optional.empty()

        when: "quando tentar salvar voto"
        service.salvar(mockRequest, mockPauta)

        then: "salvar voto"
        1 * repository.save(mockVoto)
    }

    def "quando usuário que já votou na pauta votar nela novamente, deve lançar erro de negócio"() {
        given: "voto de usuário que já não votou na pauta"
        repository.findByPautaAndAssociado(mockVoto.getPauta(), mockAssociado) >> Optional.of(mockVoto)

        when:
        service.salvar(mockRequest, mockPauta)

        then: "lançar erro de negócio"
        BusinessException ex = thrown()
        ex.getMessageKey() == ASSOCIADO_JA_VOTOU_NESTA_PAUTA
    }

}
