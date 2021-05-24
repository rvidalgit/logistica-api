package com.rodrigovidal.logisticaapi.repository;

import com.rodrigovidal.logisticaapi.model.Entrega;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends CrudRepository<Entrega, Long> {
}
