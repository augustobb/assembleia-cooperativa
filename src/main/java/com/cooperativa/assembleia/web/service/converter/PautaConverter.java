package com.cooperativa.assembleia.web.service.converter;

import com.cooperativa.assembleia.api.request.PautaRequest;
import com.cooperativa.assembleia.api.response.PautaResponse;
import com.cooperativa.assembleia.web.entity.Pauta;
import org.springframework.stereotype.Service;

@Service
public class PautaConverter extends Converter<PautaRequest, Pauta, PautaResponse> {

    public PautaConverter(PautaRequestToEntity toEntity, PautaEntityToResponse toResponse) {
        super(toEntity, toResponse);
    }
}
