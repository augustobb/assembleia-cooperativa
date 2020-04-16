package com.cooperativa.assembleia.web.task;

import com.cooperativa.assembleia.web.entity.Sessao;
import org.springframework.transaction.annotation.Transactional;

import java.util.TimerTask;

public class EncerramentoSessaoTask extends TimerTask {
    private final Sessao sessao;

    public EncerramentoSessaoTask(Sessao sessao) {
        this.sessao = sessao;
    }

    @Override
    @Transactional
    public void run() {
        sessao.encerrar();
    }
}
