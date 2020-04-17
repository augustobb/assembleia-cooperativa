package com.cooperativa.assembleia.web.service

import com.cooperativa.assembleia.web.repository.VotoRepository
import com.cooperativa.assembleia.web.service.converter.VotoRequestToEntity
import spock.lang.Specification

class VotoServiceTest extends Specification {

    VotoService service

    def repository = Mock(VotoRepository)
    def toEntity = Mock(VotoRequestToEntity)

    def mockVoto = Mock(Voto)

    void setup() {
        service = new VotoService(repository, toEntity)
    }

    def "quando usuário que ainda não votou na pauta votar nela, deve salvar voto" () {
        given: "voto de usuário que ainda não votou na pauta"

    }

    def "quando usuário que já votou na pauta votar nela novamente, deve lançar erro de negócio"() {

    }

}
