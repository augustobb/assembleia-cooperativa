package com.cooperativa.assembleia.web.service;

import com.cooperativa.assembleia.web.entity.Pauta;
import com.cooperativa.assembleia.web.entity.Resultado;
import com.cooperativa.assembleia.web.repository.PautaRepository;
import com.cooperativa.assembleia.web.repository.ResultadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResultadoService {

    private final ResultadoRepository repository;
    private final VotoService votoService;
    private final PautaRepository pautaRepository;

    public ResultadoService(ResultadoRepository repository, VotoService votoService,
                            PautaRepository pautaRepository) {
        this.repository = repository;
        this.votoService = votoService;
        this.pautaRepository = pautaRepository;
    }

    @Transactional
    public Resultado contabilizar(Pauta pauta) {
        pauta.setResultado(calcularResultado(pauta));
        registrarResultado(pauta);
        return pauta.getResultado();
    }

    private Resultado calcularResultado(Pauta pauta) {
        return Resultado.builder()
                .votosSim(votoService.countVotosSim(pauta))
                .votosNao(votoService.countVotosNao(pauta))
                .build();
    }

    private void registrarResultado(Pauta pauta) {
        Resultado resultadoSalvo = repository.save(pauta.getResultado());
        pauta.setResultado(resultadoSalvo);
        pautaRepository.save(pauta);
    }
}
