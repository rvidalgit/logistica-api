package com.rodrigovidal.logisticaapi.services;

import com.rodrigovidal.logisticaapi.model.Entrega;
import com.rodrigovidal.logisticaapi.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;

    public List<Entrega> findAll() {
        return entregaRepository.findAll();
    }

    public ResponseEntity<Entrega> busca(Long entregaId) {
        return entregaRepository.findById(entregaId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
