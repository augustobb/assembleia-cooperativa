package com.cooperativa.assembleia.web.service.converter;

import com.cooperativa.assembleia.api.request.AssociadoRequest;
import com.cooperativa.assembleia.api.response.AssociadoResponse;
import com.cooperativa.assembleia.web.entity.Associado;
import org.springframework.stereotype.Service;

@Service
public class AssociadoConverter extends Converter<AssociadoRequest, Associado, AssociadoResponse> {

    public AssociadoConverter(AssociadoRequestToEntity toEntity, AssociadoEntityToResponse toResponse) {
        super(toEntity, toResponse);
    }

}
