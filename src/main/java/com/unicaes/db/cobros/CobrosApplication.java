package com.unicaes.db.cobros;

import com.unicaes.db.cobros.repository.ProductRepository;
import com.unicaes.db.cobros.service.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CobrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CobrosApplication.class, args);

	}

}
