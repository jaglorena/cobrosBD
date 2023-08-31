package com.unicaes.db.cobros.repository;

import com.unicaes.db.cobros.enity.Transacciones;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends CrudRepository<Transacciones,Integer> {

}
