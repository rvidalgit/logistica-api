package com.rodrigovidal.logisticaapi.services;

import com.rodrigovidal.logisticaapi.exception.NegocioException;
import com.rodrigovidal.logisticaapi.model.Cliente;
import com.rodrigovidal.logisticaapi.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public ResponseEntity<Cliente> buscar(UUID clienteId) {
        return clienteRepository.findById(clienteId)
                .map(ResponseEntity::ok) //method reference
                .orElse(ResponseEntity.notFound().build());

        /*Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente.get());*/
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        if (emailEmUso) {
            throw new NegocioException("Já existe um cliente com esse e-mail");
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public ResponseEntity<Cliente> editar(Cliente clienteNew, UUID clienteId) {

        Optional<Cliente> clienteOld = clienteRepository.findById(clienteId);

        if (clienteOld.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        clienteNew.setId(clienteId);

        if (!clienteNew.getEmail().equals(clienteOld.get().getEmail())) {
            boolean emailEmUso = clienteRepository.findByEmail(clienteNew.getEmail())
                    .stream()
                    .anyMatch(clienteExistente -> !clienteExistente.equals(clienteNew));

            if (emailEmUso) {
                throw new NegocioException("Já existe um cliente com esse e-mail");
            }
        }

        return ResponseEntity.ok(clienteRepository.save(clienteNew));
    }

    @Transactional
    public ResponseEntity<Void> excluir(UUID uuid) {

        if (!clienteRepository.existsById(uuid)) {
            return ResponseEntity.notFound().build();
        }

        clienteRepository.deleteById(uuid);

        return ResponseEntity.noContent().build();
    }

}
