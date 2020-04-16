package com.cooperativa.assembleia.web.service;

import com.cooperativa.assembleia.web.entity.Pauta;
import com.cooperativa.assembleia.web.entity.Sessao;
import com.cooperativa.assembleia.web.repository.SessaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class SessaoService {

    private final SessaoRepository repository;

    public SessaoService(SessaoRepository repository){
        this.repository = repository;
    }

    @Transactional
    public void abrir(Pauta pauta, Long segundosDuracao) {
        Sessao sessao = Sessao.builder()
                .pauta(pauta)
                .dataAbertura(LocalDateTime.now())
                .segundosDuracao(segundosDuracao)
                .build();

        repository.save(sessao).agendarEncerramento();
    }
}
