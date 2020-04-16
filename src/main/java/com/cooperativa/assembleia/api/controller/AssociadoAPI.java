package com.cooperativa.assembleia.api.controller;

import com.cooperativa.assembleia.api.request.AssociadoRequest;
import com.cooperativa.assembleia.api.response.AssociadoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/associados")
public interface AssociadoAPI {

    @GetMapping
    ResponseEntity<List<AssociadoResponse>> buscarTodos();

    @GetMapping("/{id}")
    ResponseEntity<AssociadoResponse> buscarPorId(@PathVariable Long id);

    @PostMapping
    ResponseEntity<AssociadoResponse> cadastrar(@RequestBody @Valid AssociadoRequest associado);
}
