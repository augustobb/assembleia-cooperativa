package com.cooperativa.assembleia.web.entity

import com.cooperativa.assembleia.web.service.EncerramentoScheduler
import com.cooperativa.assembleia.web.service.EncerramentoSessaoService
import com.cooperativa.assembleia.web.task.EncerramentoSessaoTask
import spock.lang.Specification
import spock.lang.Unroll

class SessaoTest extends Specification {

    Sessao sessao

    def encerramentoSessaoService = Mock(EncerramentoSessaoService)
    def encerramentoScheduler = Mock(EncerramentoScheduler)

    void setup() {
        encerramentoSessaoService.getScheduler() >> encerramentoScheduler
        sessao = Sessao.builder().encerramentoService(encerramentoSessaoService).build()
    }

    @Unroll
    def "deve informar corretamente se a sessão está encerrada ou não quando o valor da flag for #encerrada"() {
        given:
        sessao.setEncerrada(encerrada)
        expect:
        sessao.isEncerrada() == encerrada
        where:
        encerrada << [true, false]
    }

    def """ao agendar encerramento de sessão, deve chamar scheduler de encerramento usando o delay definido pelos
            segundos de duração"""() {

        given: "sessão de 4 segundos de duração"
        sessao.setSegundosDuracao(4)

        when: "agendar encerramento"
        sessao.agendarEncerramento()

        then: "serviço de encerramento deve ter sido chamado com os devidos task e delay"
        1 * encerramentoScheduler.schedule(new EncerramentoSessaoTask(sessao), 4000)
    }

    def "ao encerrar sessão, serviço de encerramento deve ser chamado"() {
        when:
        sessao.encerrar()
        then:
        1 * encerramentoSessaoService.encerrar(sessao)
    }

}
