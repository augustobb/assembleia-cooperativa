package com.cooperativa.assembleia.web.controller;

import com.cooperativa.assembleia.api.controller.AssociadoAPI;
import com.cooperativa.assembleia.api.request.AssociadoRequest;
import com.cooperativa.assembleia.api.response.AssociadoResponse;
import com.cooperativa.assembleia.web.service.AssociadoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class AssociadoController implements AssociadoAPI {

    private final AssociadoService service;

    public AssociadoController(AssociadoService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<AssociadoResponse>> buscarTodos() {
        log.info("Buscando todos os associados...");
        return ResponseEntity.ok(service.buscarTodos());
    }

    @Override
    public ResponseEntity<AssociadoResponse> buscarPorId(Long id) {
        log.info("Buscando o associado {}...", id);
        return ResponseEntity.of(service.buscarOptionalResponsePorId(id));
    }

    @Override
    public ResponseEntity<AssociadoResponse> cadastrar(AssociadoRequest associado) {
        log.info("Cadastrando associado: {}...", associado.getCpf());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(associado));
    }
}
