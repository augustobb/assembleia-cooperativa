package com.cooperativa.assembleia.web.service.converter;

import com.cooperativa.assembleia.api.response.ResultadoResponse;
import com.cooperativa.assembleia.web.entity.Resultado;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ResultadoEntityToResponse implements Function<Resultado, ResultadoResponse> {
    @Override
    public ResultadoResponse apply(Resultado entity) {
        return ResultadoResponse.builder()
                .votosSim(entity.getVotosSim())
                .votosNao(entity.getVotosNao())
                .resposta(entity.getResposta())
                .build();
    }
}
