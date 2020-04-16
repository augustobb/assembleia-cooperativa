package com.cooperativa.assembleia.api.controller;

import com.cooperativa.assembleia.api.request.PautaRequest;
import com.cooperativa.assembleia.api.request.VotoRequest;
import com.cooperativa.assembleia.api.response.PautaResponse;
import com.cooperativa.assembleia.api.response.ResultadoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/pautas")
public interface PautaAPI {

    @GetMapping
    ResponseEntity<List<PautaResponse>> buscarTodas();

    @GetMapping("/{id}")
    ResponseEntity<PautaResponse> buscarPorId(@PathVariable Long id);

    @PostMapping
    ResponseEntity<PautaResponse> incluir(@RequestBody @Valid PautaRequest pauta);

    @PostMapping("/{id}/abrir-sessao")
    ResponseEntity<Void> abrirSessao(@PathVariable Long id, @RequestParam Long segundosDuracao);

    @PostMapping("/{id}/votar")
    ResponseEntity<Void> votar(@PathVariable Long id, @RequestBody @Valid VotoRequest voto);

    @GetMapping("/{id}/resultados")
    ResponseEntity<ResultadoResponse> buscarResultadoPauta(@PathVariable Long id);
}
