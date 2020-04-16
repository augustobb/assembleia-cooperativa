package com.cooperativa.assembleia.web.controller;

import com.cooperativa.assembleia.api.controller.PautaAPI;
import com.cooperativa.assembleia.api.request.PautaRequest;
import com.cooperativa.assembleia.api.request.VotoRequest;
import com.cooperativa.assembleia.api.response.PautaResponse;
import com.cooperativa.assembleia.api.response.ResultadoResponse;
import com.cooperativa.assembleia.web.service.PautaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class PautaController implements PautaAPI {

    private final PautaService service;

    public PautaController(PautaService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<PautaResponse>> buscarTodas() {
        log.info("Buscando todas as pautas...");
        return ResponseEntity.ok(service.buscarTodas());
    }

    @Override
    public ResponseEntity<PautaResponse> buscarPorId(Long id) {
        log.info("Buscando a pauta {}...", id);
        return ResponseEntity.of(service.buscarOptionalResponsePorId(id));
    }

    @Override
    public ResponseEntity<PautaResponse> incluir(PautaRequest pauta) {
        log.info("Incluindo pauta: {}...", pauta.getPergunta());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.incluir(pauta));
    }

    @Override
    public ResponseEntity<Void> abrirSessao(Long id, Long segundosDuracao) {
        log.info("Incluindo sessão para pauta: {}, duração: {}s...", id, segundosDuracao);
        service.abrirSessao(id, segundosDuracao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> votar(Long id, VotoRequest voto) {
        log.info("Incluindo voto para pauta: {}, associado: {}, resposta:{}...", id, voto.getAssociadoId(), voto.getResposta());
        service.votar(id, voto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<ResultadoResponse> buscarResultadoPauta(Long id) {
        log.info("Buscando resultado da pauta {}...", id);
        return ResponseEntity.of(service.buscarResultado(id));
    }
}
