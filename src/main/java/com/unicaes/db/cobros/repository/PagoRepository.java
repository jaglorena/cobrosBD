package com.unicaes.db.cobros.repository;

import com.unicaes.db.cobros.enity.Pago;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends CrudRepository<Pago, Integer> {
}
