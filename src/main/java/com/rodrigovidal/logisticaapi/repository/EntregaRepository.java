package com.rodrigovidal.logisticaapi.repository;

import com.rodrigovidal.logisticaapi.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}
