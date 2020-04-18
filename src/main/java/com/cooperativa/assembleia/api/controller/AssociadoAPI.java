package com.cooperativa.assembleia.api.controller;

import com.cooperativa.assembleia.api.request.AssociadoRequest;
import com.cooperativa.assembleia.api.response.AssociadoResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/associados")
@RestController
public interface AssociadoAPI {

    @GetMapping
    @ApiOperation(value = "Retorna todos os associados", response = AssociadoResponse.class, responseContainer = "List")
    ResponseEntity<List<AssociadoResponse>> buscarTodos();

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca associado pelo identificador", response = AssociadoResponse.class)
    ResponseEntity<AssociadoResponse> buscarPorId(
            @PathVariable @ApiParam(value = "Identificador do associado", required = true) Long id);

    @PostMapping
    @ApiOperation(value = "Cadastra um novo associado, retornando associado cadastrado", response = AssociadoResponse.class)
    ResponseEntity<AssociadoResponse> cadastrar(
            @RequestBody
            @ApiParam(value="Corpo de cadastro do associado", required=true)
            @Valid AssociadoRequest associado);
}
