package com.unicaes.db.cobros.repository;

import com.unicaes.db.cobros.enity.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Producto, Integer> {
}
