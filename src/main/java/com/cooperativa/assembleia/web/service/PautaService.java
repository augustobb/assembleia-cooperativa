package com.cooperativa.assembleia.web.service;

import com.cooperativa.assembleia.api.request.PautaRequest;
import com.cooperativa.assembleia.api.request.VotoRequest;
import com.cooperativa.assembleia.api.response.PautaResponse;
import com.cooperativa.assembleia.api.response.ResultadoResponse;
import com.cooperativa.assembleia.web.entity.Pauta;
import com.cooperativa.assembleia.web.repository.PautaRepository;
import com.cooperativa.assembleia.web.service.converter.PautaConverter;
import com.cooperativa.assembleia.web.service.converter.ResultadoEntityToResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PautaService {

    private final PautaRepository repository;
    private final PautaConverter converter;
    private final VotoService votoService;
    private final SessaoService sessaoService;
    private final ResultadoEntityToResponse resultadoEntityToResponse;

    public PautaService(PautaRepository repository, PautaConverter converter, VotoService votoService,
                        SessaoService sessaoService, ResultadoEntityToResponse resultadoEntityToResponse) {
        this.repository = repository;
        this.converter = converter;
        this.votoService = votoService;
        this.sessaoService = sessaoService;
        this.resultadoEntityToResponse = resultadoEntityToResponse;
    }

    @Transactional(readOnly = true)
    public List<PautaResponse> buscarTodas() {
        return repository.findAll()
                .stream()
                .map(converter::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<PautaResponse> buscarOptionalResponsePorId(Long id) {
        return repository.findById(id).map(converter::toResponse);
    }

    @Transactional
    public PautaResponse incluir(PautaRequest request) {
        Pauta pauta = repository.save(converter.toEntity(request));
        return converter.toResponse(pauta);
    }

    private Pauta buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            String message = "Não foi possível encontrar pauta com identificador " + id;
            log.error(message);
            return new InvalidParameterException(message);
        });
    }

    @Transactional
    public void abrirSessao(Long id, Long segundosDuracao) {
        Pauta pauta = buscarPorId(id);
        sessaoService.abrir(pauta, segundosDuracao);
    }

    @Transactional
    public void votar(Long id, VotoRequest voto) {
        votoService.salvar(voto, buscarPorId(id));
    }

    @Transactional(readOnly = true)
    public Optional<ResultadoResponse> buscarResultado(Long id) {
        Optional<Pauta> pautaEncontrada = repository.findById(id);

        return pautaEncontrada.map(Pauta::getResultado)
                .map(resultadoEntityToResponse::apply)
                .map(resultadoResponse -> {
                    resultadoResponse.setPauta(converter.toResponse(pautaEncontrada.get()));
                    return resultadoResponse;
                });
    }

}
