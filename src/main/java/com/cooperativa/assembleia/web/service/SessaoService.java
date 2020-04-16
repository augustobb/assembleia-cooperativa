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
    private final EncerramentoScheduler encerramentoScheduler;

    public SessaoService(SessaoRepository repository, EncerramentoSessaoService encerramentoService,
                         EncerramentoScheduler encerramentoScheduler){
        this.repository = repository;
        this.encerramentoService = encerramentoService;
        this.encerramentoScheduler = encerramentoScheduler;
    }

    @Transactional
    public void abrir(Pauta pauta, Long segundosDuracao) {
        assertPautaSemSessoes(pauta);

        Sessao sessao = Sessao.builder()
                .pauta(pauta)
                .dataHoraAbertura(LocalDateTime.now())
                .segundosDuracao(segundosDuracao)
                .encerrada(false)
                .encerramentoService(encerramentoService)
                .build();

        log.info("ABRINDO Pauta:{}, DURACAO: {}", pauta.getId(), segundosDuracao);
        repository.save(sessao).agendarEncerramento(encerramentoScheduler);
    }

    private void assertPautaSemSessoes(Pauta pauta) {
        repository.findByPauta(pauta).ifPresent(s -> {
            throw new BusinessException(SESSAO_JA_ABERTA_PARA_PAUTA, pauta.getId());
        });
    }
}
