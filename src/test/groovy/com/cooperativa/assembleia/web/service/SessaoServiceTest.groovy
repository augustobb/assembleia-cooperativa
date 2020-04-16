package com.cooperativa.assembleia.web.service

import com.cooperativa.assembleia.web.entity.Pauta
import com.cooperativa.assembleia.web.entity.Sessao
import com.cooperativa.assembleia.web.exception.BusinessException
import com.cooperativa.assembleia.web.repository.SessaoRepository
import spock.lang.Specification

import java.time.LocalDateTime

import static com.cooperativa.assembleia.web.message.MessageKey.SESSAO_JA_ABERTA_PARA_PAUTA;

class SessaoServiceTest extends Specification {

    SessaoService service

    def repository = Mock(SessaoRepository)
    def encerramentoService = Mock(EncerramentoSessaoService)

    def mockSessao = Mock(Sessao)

    void setup() {
        service = new SessaoService(repository, encerramentoService)
    }

    def """dado uma pauta sem sessões e a quantidade de segundos para durar, quando solicitar abertura,
            deve criar uma sessão para a pauta"""() {
        Sessao sessaoCriada = null

        given: "pauta sem sessões"
        def pauta = Pauta.builder().id(1L).build()
        repository.findByPauta(pauta) >> Optional.empty()

        and: "quantidade de segundos para durar"
        def segundosDuracao = 30

        when: "solicitar abertura"
        service.abrir(pauta, segundosDuracao)

        then: "criar sessão com atributos corretos"
        1 * repository.save(_) >> {List args -> sessaoCriada=args[0] as Sessao}
        sessaoCriada.getPauta() == pauta
        sessaoCriada.getSegundosDuracao() == segundosDuracao
        sessaoCriada.getDataHoraAbertura().isBefore(LocalDateTime.now())
        !sessaoCriada.isEncerrada()
    }

    def """dado uma pauta sem sessões e a quantidade de segundos para durar, quando solicitar abertura,
            deve agendar o encerramento da sessão criada"""() {

        given: "pauta sem sessões"
        def pauta = Pauta.builder().id(1L).build()
        repository.findByPauta(pauta) >> Optional.empty()

        and: "quantidade de segundos para durar"
        def segundosDuracao = 30

        when: "solicitar abertura"
        service.abrir(pauta, segundosDuracao)

        then: "agendar encerramento da sessão criada"
        1 * repository.save(_) >> mockSessao
        1 * mockSessao.agendarEncerramento()
    }

    def """dado uma pauta com sessão existente e a quantidade de segundos para durar, quando solicitar abertura,
            deve lançar erro de negócio"""() {

        given: "pauta com sessão existente"
        def pauta = Pauta.builder().id(1L).build()
        repository.findByPauta(pauta) >> Optional.of(mockSessao)

        and: "quantidade de segundos para durar"
        def segundosDuracao = 30

        when: "solicitar abertura"
        service.abrir(pauta, segundosDuracao)

        then:
        BusinessException ex = thrown()
        ex.getMessageKey() == SESSAO_JA_ABERTA_PARA_PAUTA
    }
}
