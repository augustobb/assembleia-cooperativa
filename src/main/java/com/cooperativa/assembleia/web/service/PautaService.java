package com.cooperativa.assembleia.web.service;

import com.cooperativa.assembleia.api.dto.Pauta;
import com.cooperativa.assembleia.web.entity.PautaEntity;
import com.cooperativa.assembleia.web.repository.PautaRepository;
import com.cooperativa.assembleia.web.service.converter.PautaConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PautaService {

    private final PautaRepository repository;
    private final PautaConverter converter;

    @Autowired
    public PautaService(PautaRepository repository, PautaConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public List<Pauta> buscarTodas() {
        return repository.findAll().stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<Pauta> buscarPorId(Long id) {
        return repository.findById(id).map(converter::toDTO);
    }

    public Optional<Pauta> buscarUltima() {
        return repository.findFirstByOrderByCriacaoDesc().map(converter::toDTO);
    }

    public Pauta incluir(Pauta pauta) {
        PautaEntity pautaCriada = repository.save(converter.toEntity(pauta));
        return converter.toDTO(pautaCriada);
    }

}
