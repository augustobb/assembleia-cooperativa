package com.cooperativa.assembleia.web.service;

import com.cooperativa.assembleia.web.entity.Pauta;
import com.cooperativa.assembleia.web.entity.Resultado;
import com.cooperativa.assembleia.web.repository.PautaRepository;
import com.cooperativa.assembleia.web.repository.ResultadoRepository;
import com.cooperativa.assembleia.web.repository.VotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResultadoService {

    private final ResultadoRepository repository;
    private final VotoRepository votoRepository;
    private final PautaRepository pautaRepository;

    public ResultadoService(ResultadoRepository repository, VotoRepository votoRepository,
                            PautaRepository pautaRepository) {
        this.repository = repository;
        this.votoRepository = votoRepository;
        this.pautaRepository = pautaRepository;
    }

    @Transactional
    public Resultado contabilizar(Pauta pauta) {
        pauta.setResultado(calcularResultado(pauta));
        registrarResultado(pauta);
        return pauta.getResultado();
    }

    private Resultado calcularResultado(Pauta pauta) {
        return Resultado.builder().build();
    }

    private void registrarResultado(Pauta pauta) {
        Resultado resultadoSalvo = repository.save(pauta.getResultado());
        pauta.setResultado(resultadoSalvo);
        pautaRepository.save(pauta);
    }
}
