package com.cooperativa.assembleia.api.controller;

import com.cooperativa.assembleia.api.request.PautaRequest;
import com.cooperativa.assembleia.api.request.VotoRequest;
import com.cooperativa.assembleia.api.response.PautaResponse;
import com.cooperativa.assembleia.api.response.ResultadoResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RequestMapping("/pautas")
public interface PautaAPI {

    @GetMapping
    @ApiOperation(value = "Retorna todas as pautas", response = PautaResponse.class, responseContainer = "List")
    ResponseEntity<List<PautaResponse>> buscarTodas();

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca pauta pelo identificador", response = PautaResponse.class)
    ResponseEntity<PautaResponse> buscarPorId(@PathVariable Long id);

    @PostMapping
    @ApiOperation(value = "Inclui uma pauta, retornando pauta criada", response = PautaResponse.class)
    ResponseEntity<PautaResponse> incluir(
            @RequestBody @Valid @ApiParam(value="Corpo da pauta", required=true) PautaRequest pauta);

    @PostMapping("/{id}/abrir-sessao")
    @ApiOperation(value = "Abre sessão para a pauta")
    ResponseEntity<Void> abrirSessao(@PathVariable Long id,
            @Min(value = 10, message = "Duração mínima: 10s")
            @ApiParam(value = "Duração da pauta, em segundos", defaultValue = "60")
            @RequestParam(defaultValue="60") Long segundosDuracao);

    @PostMapping("/{id}/votar")
    @ApiOperation(value = "Registra voto para pauta")
    ResponseEntity<Void> votar(
            @PathVariable Long id,
            @Valid @RequestBody @ApiParam(value="Corpo do voto", required=true) VotoRequest voto);

    @GetMapping("/{id}/resultados")
    @ApiOperation(value = "Obtém resultados da pauta", response = ResultadoResponse.class)
    ResponseEntity<ResultadoResponse> buscarResultadoPauta(@PathVariable Long id);
}
