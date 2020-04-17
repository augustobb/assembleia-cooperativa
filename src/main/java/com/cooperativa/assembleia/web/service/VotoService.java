package com.cooperativa.assembleia.web.service;

import com.cooperativa.assembleia.api.request.VotoRequest;
import com.cooperativa.assembleia.web.entity.Pauta;
import com.cooperativa.assembleia.web.entity.Voto;
import com.cooperativa.assembleia.web.enums.Resposta;
import com.cooperativa.assembleia.web.exception.BusinessException;
import com.cooperativa.assembleia.web.repository.VotoRepository;
import com.cooperativa.assembleia.web.service.converter.VotoRequestToEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.cooperativa.assembleia.web.message.MessageKey.ASSOCIADO_JA_VOTOU_NESTA_PAUTA;

@Service
public class VotoService {

    private final VotoRepository repository;
    private final VotoRequestToEntity toEntity;

    public VotoService(VotoRepository repository, VotoRequestToEntity toEntity){
        this.repository = repository;
        this.toEntity = toEntity;
    }

    @Transactional
    public void salvar(VotoRequest request, Pauta pauta) {
        Voto voto = toEntity.apply(request);
        voto.validarHabilitacaoDoAssociado();
        voto.setPauta(pauta);
        assertUsuarioNaoVotouAinda(voto);
        repository.save(voto);
    }

    private void assertUsuarioNaoVotouAinda(Voto voto) {
        repository.findByPautaAndAssociado(voto.getPauta(), voto.getAssociado()).ifPresent(v -> {
            throw new BusinessException(ASSOCIADO_JA_VOTOU_NESTA_PAUTA);
        });
    }

    @Transactional(readOnly = true)
    public Long countVotosSim(Pauta pauta) {
        return repository.countByPautaAndResposta(pauta, Resposta.SIM);
    }

    @Transactional(readOnly = true)
    public Long countVotosNao(Pauta pauta) {
        return repository.countByPautaAndResposta(pauta, Resposta.NAO);
    }
}
