package com.cooperativa.assembleia.web.service.converter;

import com.cooperativa.assembleia.api.request.PautaRequest;
import com.cooperativa.assembleia.api.response.PautaResponse;
import com.cooperativa.assembleia.web.entity.Pauta;
import org.springframework.stereotype.Service;

@Service
public class PautaConverter  {

    private final PautaRequestToEntity toEntity;
    private final PautaEntityToResponse toResponse;

    public PautaConverter(PautaRequestToEntity toEntity, PautaEntityToResponse toResponse) {
        this.toEntity = toEntity;
        this.toResponse = toResponse;
    }

    public Pauta toEntity(PautaRequest request) {
        return toEntity.apply(request);
    }

    public PautaResponse toResponse(Pauta entity) {
        return toResponse.apply(entity);
    }
}
