package com.rodrigovidal.logisticaapi.services;

import com.rodrigovidal.logisticaapi.exception.NegocioException;
import com.rodrigovidal.logisticaapi.model.Cliente;
import com.rodrigovidal.logisticaapi.model.Entrega;
import com.rodrigovidal.logisticaapi.model.StatusEntrega;
import com.rodrigovidal.logisticaapi.repository.ClienteRepository;
import com.rodrigovidal.logisticaapi.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

    private final EntregaRepository entregaRepository;
    private final ClienteRepository clienteRepository;

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        carregaCliente(entrega);
        entrega.setDataPedido(LocalDateTime.now());
        entrega.setStatus(StatusEntrega.PENDENTE);
        return entregaRepository.save(entrega);
    }

    private void carregaCliente(Entrega entrega) throws NegocioException {
        Cliente cliente = clienteRepository.findById(entrega.getCliente().getId()).orElseThrow(() -> new NegocioException("Cliente não encontrado"));
        entrega.setCliente(cliente);
    }
}
