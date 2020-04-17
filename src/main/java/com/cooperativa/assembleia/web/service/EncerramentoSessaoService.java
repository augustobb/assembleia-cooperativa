package com.cooperativa.assembleia.web.service;

import com.cooperativa.assembleia.web.entity.Pauta;
import com.cooperativa.assembleia.web.entity.Resultado;
import com.cooperativa.assembleia.web.entity.Sessao;
import com.cooperativa.assembleia.web.repository.SessaoRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class EncerramentoSessaoService {

    private final SessaoRepository repository;
    private final ResultadoService resultadoService;
    @Getter
    private final EncerramentoScheduler scheduler;

    public EncerramentoSessaoService(SessaoRepository repository, ResultadoService resultadoService,
                                     EncerramentoScheduler scheduler) {
        this.repository = repository;
        this.resultadoService = resultadoService;
        this.scheduler = scheduler;
    }

    @Transactional
    public void encerrar(Sessao sessao) {
        salvarEncerramento(sessao);
        Pauta pauta = sessao.getPauta();
        Resultado resultado = contabilizarResultado(sessao.getPauta());
        log.info("Acaba de ser encerrada a Pauta {} ({}), e o resultado foi {}, com {} votos a favor e {} votos contra",
                pauta.getId(), pauta.getPergunta(), resultado.getResposta(), resultado.getVotosSim(), resultado.getVotosNao());
    }

    private void salvarEncerramento(Sessao sessao) {
        sessao.setEncerrada(true);
        repository.save(sessao);
    }

    private Resultado contabilizarResultado(Pauta pauta) {
        return resultadoService.contabilizar(pauta);
    }
}
