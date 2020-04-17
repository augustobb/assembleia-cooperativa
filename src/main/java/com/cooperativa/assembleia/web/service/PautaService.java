package com.cooperativa.assembleia.web.service;

import com.cooperativa.assembleia.api.request.PautaRequest;
import com.cooperativa.assembleia.api.request.VotoRequest;
import com.cooperativa.assembleia.api.response.PautaResponse;
import com.cooperativa.assembleia.api.response.ResultadoResponse;
import com.cooperativa.assembleia.web.entity.Pauta;
import com.cooperativa.assembleia.web.repository.PautaRepository;
import com.cooperativa.assembleia.web.service.converter.PautaConverter;
import com.cooperativa.assembleia.web.service.converter.ResultadoEntityToResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private Pauta getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
            new InvalidParameterException("Não foi possível encontrar pauta com identificador " + id)
        );
    }

    @Transactional
    public void abrirSessao(Long id, Long segundosDuracao) {
        Pauta pauta = sessaoService.withInfoSessao(getById(id));
        pauta.validarSemSessaoExistente();
        sessaoService.abrir(pauta, segundosDuracao);
    }

    @Transactional
    public void votar(Long id, VotoRequest voto) {
        Pauta pauta = sessaoService.withInfoSessao(getById(id));
        pauta.validarPautaAberta();
        votoService.salvar(voto, pauta);
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
