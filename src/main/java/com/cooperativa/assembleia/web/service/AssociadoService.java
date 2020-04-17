package com.cooperativa.assembleia.web.service;

import com.cooperativa.assembleia.api.request.AssociadoRequest;
import com.cooperativa.assembleia.api.response.AssociadoResponse;
import com.cooperativa.assembleia.web.entity.Associado;
import com.cooperativa.assembleia.web.integration.client.UserInfoClient;
import com.cooperativa.assembleia.web.repository.AssociadoRepository;
import com.cooperativa.assembleia.web.service.converter.AssociadoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.cooperativa.assembleia.web.integration.enums.UserInfoStatus.ABLE_TO_VOTE;

@Service
public class AssociadoService {

    private final AssociadoRepository repository;
    private final AssociadoConverter converter;
    private final UserInfoClient userInfoClient;

    public AssociadoService(AssociadoRepository repository, AssociadoConverter converter, UserInfoClient userInfoClient) {
        this.repository = repository;
        this.converter = converter;
        this.userInfoClient = userInfoClient;
    }

    @Transactional(readOnly = true)
    public List<AssociadoResponse> buscarTodos() {
        return repository.findAll()
                .stream()
                .map(converter::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<AssociadoResponse> buscarOptionalResponsePorId(Long id) {
        return repository.findById(id).map(converter::toResponse);
    }

    @Transactional
    public AssociadoResponse cadastrar(AssociadoRequest request) {
        Associado associado = repository.save(converter.toEntity(request));
        return converter.toResponse(associado);
    }

    private Associado getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
            new InvalidParameterException("Não foi possível encontrar associado com identificador " + id)
        );
    }

    private Associado withInfoHabilitacaoVoto(Associado associado) {
        String statusCpf = userInfoClient.getByCpf(associado.getCpf());
        associado.setPodeVotar(ABLE_TO_VOTE.name().equals(statusCpf));
        return associado;
    }

    @Transactional(readOnly = true)
    public Associado buscarComDetalhesHabilitacaoVoto(Long id) {
        return withInfoHabilitacaoVoto(getById(id));
    }

}
