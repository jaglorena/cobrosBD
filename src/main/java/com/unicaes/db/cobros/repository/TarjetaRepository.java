package com.unicaes.db.cobros.repository;

import com.unicaes.db.cobros.enity.Tarjeta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaRepository extends CrudRepository<Tarjeta, Integer> {
}
