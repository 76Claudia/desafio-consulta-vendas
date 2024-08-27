package com.devsuperior.dsmeta;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

@SpringBootApplication
public class DsmetaApplication implements CommandLineRunner {
	@Autowired
	private SaleRepository repository;
	private Pageable pageable;

	public static void main(String[] args) {
		SpringApplication.run(DsmetaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Page<SaleMinDTO> page = repository.getReport();

		for (SaleMinDTO obj : page) {
            System.out.println(obj);
        }

	}

}