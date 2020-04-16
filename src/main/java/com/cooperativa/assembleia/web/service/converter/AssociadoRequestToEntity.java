package com.cooperativa.assembleia.web.service.converter;

import com.cooperativa.assembleia.api.request.AssociadoRequest;
import com.cooperativa.assembleia.web.entity.Associado;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AssociadoRequestToEntity implements Function<AssociadoRequest, Associado> {

    @Override
    public Associado apply(AssociadoRequest request) {
        return Associado.builder().cpf(request.getCpf()).build();
    }
}
