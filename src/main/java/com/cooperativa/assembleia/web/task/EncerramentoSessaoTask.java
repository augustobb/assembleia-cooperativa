package com.cooperativa.assembleia.web.task;

import com.cooperativa.assembleia.web.entity.Sessao;
import com.cooperativa.assembleia.web.repository.SessaoRepository;
import com.cooperativa.assembleia.web.repository.VotoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.TimerTask;

@Slf4j
public class EncerramentoSessaoTask extends TimerTask {
    private SessaoRepository repository;
    private VotoRepository votoRepository;
    private final Sessao sessao;

    public EncerramentoSessaoTask(Sessao sessao) {
        this.sessao = sessao;
    }

    @Override
    @Transactional
    public void run() {
        sessao.setEncerrada(true);
        repository.save(sessao);
        log.info("Acaba de ser encerrada a Pauta {}, {}", sessao.getPauta().getId(), sessao.getPauta().getPergunta());
    }
}
