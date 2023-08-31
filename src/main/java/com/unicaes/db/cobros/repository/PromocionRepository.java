package com.unicaes.db.cobros.repository;

import com.unicaes.db.cobros.enity.DetallesPromocion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocionRepository extends CrudRepository<DetallesPromocion, Integer> {
}
