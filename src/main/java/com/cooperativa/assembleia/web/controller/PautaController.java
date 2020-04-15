package com.cooperativa.assembleia.web.controller;

import com.cooperativa.assembleia.api.controller.PautaAPI;
import com.cooperativa.assembleia.api.dto.Pauta;
import com.cooperativa.assembleia.api.dto.Resultado;
import com.cooperativa.assembleia.api.dto.Voto;
import com.cooperativa.assembleia.web.service.PautaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class PautaController implements PautaAPI {

    private final PautaService service;

    @Autowired
    public PautaController(PautaService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<Pauta>> buscarTodas() {
        log.info("Buscando todas as pautas...");
        return ResponseEntity.ok(service.buscarTodas());
    }

    @Override
    public ResponseEntity<Pauta> buscarPorId(Long id) {
        log.info("Buscando a pauta {}...", id);
        return ResponseEntity.of(service.buscarPorId(id));
    }

    @Override
    public ResponseEntity<Pauta> buscarUltima() {
        log.info("Buscando a última pauta...");
        return ResponseEntity.of(service.buscarUltima());
    }

    @Override
    public ResponseEntity<Pauta> incluir(@Valid Pauta pauta) {
        log.info("Incluindo pauta: {}...", pauta.getPergunta());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.incluir(pauta));
    }

    @Override
    public ResponseEntity<Void> votar(Long id, @Valid Voto voto) {
        log.info("Incluindo voto para pauta: {}, associado: {}, resposta:{}...", id, voto.getCpfAssociado(), voto.getResposta());
        return null;
    }

    @Override
    public ResponseEntity<Resultado> buscarResultadoPauta(Long id) {
        log.info("Buscando resultado da pauta {}...", id);
        return null;
    }

    @Override
    public ResponseEntity<Resultado> buscarResultadoUltimaPauta() {
        log.info("Buscando resultado da última pauta...");
        return null;
    }
}
