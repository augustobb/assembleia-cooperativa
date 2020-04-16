package com.cooperativa.assembleia.web.service.converter;

import com.cooperativa.assembleia.api.response.AssociadoResponse;
import com.cooperativa.assembleia.web.entity.Associado;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AssociadoEntityToResponse implements Function<Associado, AssociadoResponse> {
    @Override
    public AssociadoResponse apply(Associado entity) {
        return AssociadoResponse.builder()
                .id(entity.getId())
                .cpf(entity.getCpf())
                .build();
    }
}
