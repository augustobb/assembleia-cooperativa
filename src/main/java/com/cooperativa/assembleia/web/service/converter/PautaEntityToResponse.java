package com.cooperativa.assembleia.web.service.converter;

import com.cooperativa.assembleia.api.response.PautaResponse;
import com.cooperativa.assembleia.web.entity.Pauta;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PautaEntityToResponse implements Function<Pauta, PautaResponse> {

    @Override
    public PautaResponse apply(Pauta entity) {
        return PautaResponse.builder()
                .id(entity.getId())
                .pergunta(entity.getPergunta())
                .build();
    }
}
