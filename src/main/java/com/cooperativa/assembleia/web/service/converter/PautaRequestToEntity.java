package com.cooperativa.assembleia.web.service.converter;

import com.cooperativa.assembleia.api.request.PautaRequest;
import com.cooperativa.assembleia.web.entity.Pauta;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PautaRequestToEntity implements Function<PautaRequest, Pauta> {
    @Override
    public Pauta apply(PautaRequest request) {
        return Pauta.builder().pergunta(request.getPergunta()).build();
    }
}
