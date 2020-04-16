package com.cooperativa.assembleia.web.service.converter;

import com.cooperativa.assembleia.api.request.VotoRequest;
import com.cooperativa.assembleia.web.entity.Voto;
import com.cooperativa.assembleia.web.enums.Resposta;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class VotoRequestToEntity implements Function<VotoRequest, Voto> {

    @Override
    public Voto apply(VotoRequest request) {
        return Voto.builder()
                .resposta(Resposta.valueOf(request.getResposta()))
                .build();
    }
}
