package com.cooperativa.assembleia.web.service;

import com.cooperativa.assembleia.api.request.AssociadoRequest;
import com.cooperativa.assembleia.api.response.AssociadoResponse;
import com.cooperativa.assembleia.web.entity.Associado;
import com.cooperativa.assembleia.web.repository.AssociadoRepository;
import com.cooperativa.assembleia.web.service.converter.AssociadoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssociadoService {

    private final AssociadoRepository repository;
    private final AssociadoConverter converter;

    public AssociadoService(AssociadoRepository repository, AssociadoConverter converter) {
        this.repository = repository;
        this.converter = converter;
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
}
