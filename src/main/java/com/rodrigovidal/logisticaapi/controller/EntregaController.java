package com.rodrigovidal.logisticaapi.controller;

import com.rodrigovidal.logisticaapi.model.Entrega;
import com.rodrigovidal.logisticaapi.services.EntregaService;
import com.rodrigovidal.logisticaapi.services.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private final SolicitacaoEntregaService solicitacaoEntregaService;
    private final EntregaService entregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
        return solicitacaoEntregaService.solicitar(entrega);
    }

    @GetMapping
    public List<Entrega> listar() {
        return entregaService.findAll();
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<Entrega> buscar(@PathVariable Long entregaId) {
        return entregaService.busca(entregaId);
    }
}
