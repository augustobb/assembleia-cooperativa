package com.cooperativa.assembleia.api.controller;

import com.cooperativa.assembleia.api.dto.Pauta;
import com.cooperativa.assembleia.api.dto.Resultado;
import com.cooperativa.assembleia.api.dto.Voto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/pautas")
public interface PautaAPI {

    @GetMapping
    ResponseEntity<List<Pauta>> buscarTodas();

    @GetMapping("/{id}")
    ResponseEntity<Pauta> buscarPorId(@PathVariable Long id);

    @GetMapping("/ultima")
    ResponseEntity<Pauta> buscarUltima();

    @PostMapping
    ResponseEntity<Pauta> incluir(@RequestBody @Valid Pauta pauta);

    @PostMapping("/{id}/votar")
    ResponseEntity<Void> votar(@PathVariable Long id, @RequestBody @Valid Voto voto);

    @GetMapping("/{id}/resultados")
    ResponseEntity<Resultado> buscarResultadoPauta(@PathVariable Long id);

    @GetMapping("/ultima/resultados")
    ResponseEntity<Resultado> buscarResultadoUltimaPauta();
}
