package com.rodrigovidal.logisticaapi.services;

import com.rodrigovidal.logisticaapi.exception.NegocioException;
import com.rodrigovidal.logisticaapi.model.Cliente;
import com.rodrigovidal.logisticaapi.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transient
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
    public void excluir(UUID uuid) {
        clienteRepository.deleteById(uuid);
    }

}
