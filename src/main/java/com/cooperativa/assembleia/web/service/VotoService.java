package com.cooperativa.assembleia.web.service;

import com.cooperativa.assembleia.api.request.VotoRequest;
import com.cooperativa.assembleia.web.entity.Pauta;
import com.cooperativa.assembleia.web.enums.Resposta;
import com.cooperativa.assembleia.web.repository.VotoRepository;
import com.cooperativa.assembleia.web.service.converter.VotoRequestToEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        repository.save(toEntity.apply(request));
    }

    public Long countVotosSim(Pauta pauta) {
        return repository.countByPautaAndResposta(pauta, Resposta.SIM);
    }

    public Long countVotosNao(Pauta pauta) {
        return repository.countByPautaAndResposta(pauta, Resposta.NAO);
    }
}
