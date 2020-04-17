package com.cooperativa.assembleia.web.service

import com.cooperativa.assembleia.web.entity.Pauta
import com.cooperativa.assembleia.web.entity.Sessao
import com.cooperativa.assembleia.web.repository.SessaoRepository
import spock.lang.Specification

import java.time.LocalDateTime

class SessaoServiceTest extends Specification {

    SessaoService service

    def repository = Mock(SessaoRepository)
    def encerramentoService = Mock(EncerramentoSessaoService)

    def mockSessao = Mock(Sessao)

    void setup() {
        encerramentoService.getScheduler() >> Mock(EncerramentoScheduler)
        service = new SessaoService(repository, encerramentoService)
    }

    def "dado uma pauta, quando solicitar abertura, deve criar uma sessão para a pauta"() {
        Sessao sessaoCriada = null

        given: "pauta"
        def pauta = Pauta.builder().id(1L).build()

        and: "quantidade de segundos para durar"
        def segundosDuracao = 30

        when: "solicitar abertura"
        service.abrir(pauta, segundosDuracao)

        then: "criar sessão com atributos corretos"
        1 * repository.save(_) >> {List args -> sessaoCriada=args[0] as Sessao}
        sessaoCriada.getPauta() == pauta
        sessaoCriada.getSegundosDuracao() == segundosDuracao
        sessaoCriada.getDataHoraAbertura().isBefore(LocalDateTime.now())
    }

    def "dado uma pauta, quando solicitar abertura, deve agendar o encerramento da sessão criada"() {
        given: "pauta"
        def pauta = Pauta.builder().id(1L).build()

        and: "quantidade de segundos para durar"
        def segundosDuracao = 30

        when: "solicitar abertura"
        service.abrir(pauta, segundosDuracao)

        then: "agendar encerramento da sessão criada"
        1 * repository.save(_) >> mockSessao
        1 * mockSessao.agendarEncerramento()
    }
}
