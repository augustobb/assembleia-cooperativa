package com.cooperativa.assembleia.web.task;

import com.cooperativa.assembleia.web.entity.Sessao;
import lombok.EqualsAndHashCode;

import java.util.TimerTask;

@EqualsAndHashCode(callSuper = false)
public class EncerramentoSessaoTask extends TimerTask {
    private final Sessao sessao;

    public EncerramentoSessaoTask(Sessao sessao) {
        this.sessao = sessao;
    }

    @Override
    public void run() {
        sessao.encerrar();
    }
}
