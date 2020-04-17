package com.cooperativa.assembleia.web.service;

import com.cooperativa.assembleia.web.entity.Pauta;
import com.cooperativa.assembleia.web.entity.Sessao;
import com.cooperativa.assembleia.web.exception.BusinessException;
import com.cooperativa.assembleia.web.repository.SessaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.cooperativa.assembleia.web.message.MessageKey.SESSAO_JA_ABERTA_PARA_PAUTA;

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
        assertPautaSemSessoes(pauta);

        Sessao sessao = Sessao.builder()
                .pauta(pauta)
                .dataHoraAbertura(LocalDateTime.now())
                .segundosDuracao(segundosDuracao)
                .encerramentoService(encerramentoService)
                .build();

        repository.save(sessao).agendarEncerramento();
    }

    private void assertPautaSemSessoes(Pauta pauta) {
        repository.findByPauta(pauta).ifPresent(s -> {
            throw new BusinessException(SESSAO_JA_ABERTA_PARA_PAUTA, pauta.getId());
        });
    }
}
