package com.cooperativa.assembleia.web.service;

import com.cooperativa.assembleia.web.entity.Pauta;
import com.cooperativa.assembleia.web.entity.Sessao;
import com.cooperativa.assembleia.web.repository.SessaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
public class SessaoService {

    private final SessaoRepository repository;
    private final EncerramentoSessaoService encerramentoService;

    public SessaoService(SessaoRepository repository, EncerramentoSessaoService encerramentoService){
        this.repository = repository;
        this.encerramentoService = encerramentoService;
    }

    @Transactional
    public void abrir(Pauta pauta, Long segundosDuracao) {
        Sessao sessao = Sessao.builder()
                .pauta(pauta)
                .dataAbertura(LocalDateTime.now())
                .segundosDuracao(segundosDuracao)
                .encerrada(false)
                .encerramentoService(encerramentoService)
                .build();

        log.info("ABRINDO Pauta:{}, DURACAO: {}", pauta.getId(), segundosDuracao);
        repository.save(sessao).agendarEncerramento();
    }
}
