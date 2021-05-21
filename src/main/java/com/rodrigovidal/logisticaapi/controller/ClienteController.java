package com.rodrigovidal.logisticaapi.controller;

import com.rodrigovidal.logisticaapi.model.Cliente;
import com.rodrigovidal.logisticaapi.repository.ClienteRepository;
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

    private final ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable UUID clienteId) {
        return clienteRepository.findById(clienteId)
                .map(ResponseEntity::ok) //method reference
                .orElse(ResponseEntity.notFound().build());

        /*Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente.get());*/
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente criar(@Valid @RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> alterar(@RequestBody Cliente cliente,
                                           @PathVariable UUID clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(clienteId);

        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deleter(@PathVariable UUID clienteId) {

        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        clienteRepository.deleteById(clienteId);

        return ResponseEntity.noContent().build();
    }
}
