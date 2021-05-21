package com.rodrigovidal.logisticaapi.repository;

import com.rodrigovidal.logisticaapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    List<Cliente> findByNome(String nome);

    //usa o like no nome
    List<Cliente> findByNomeContaining(String nome);
}
