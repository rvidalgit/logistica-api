package com.rodrigovidal.logisticaapi.controller;

import com.rodrigovidal.logisticaapi.model.Cliente;
import com.rodrigovidal.logisticaapi.services.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable UUID clienteId) {
        return clienteService.buscar(clienteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente criar(@Valid @RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> alterar(@RequestBody Cliente cliente,
                                           @PathVariable UUID clienteId) {
        return clienteService.editar(cliente, clienteId);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deletar(@PathVariable UUID clienteId) {
        return clienteService.excluir(clienteId);
    }
}
