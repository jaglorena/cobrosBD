package com.unicaes.db.cobros.repository;

import com.unicaes.db.cobros.enity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends CrudRepository<Cliente, Integer> {
}
