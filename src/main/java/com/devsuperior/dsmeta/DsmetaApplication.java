package com.devsuperior.dsmeta;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SellerSumDTO;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@SpringBootApplication
@EnableJpaRepositories
public class DsmetaApplication implements CommandLineRunner {
	@Autowired
	private SaleRepository repository;
	private Pageable pageable;


	public static void main(String[] args) {
		SpringApplication.run(DsmetaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		LocalDate minDate = LocalDate.now();
		LocalDate maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		String name = "";
		Page<SaleMinDTO> page = repository.getReport(minDate, maxDate, name, pageable);
		System.out.println("Relatorio de vendas");

		for (SaleMinDTO obj : page) {
			System.out.println(obj);
		}


		LocalDate minDate2 = LocalDate.now();
		LocalDate maxDate2 = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		Page<SellerSumDTO> page2 = repository.getSummary(minDate2, maxDate2, pageable);
		System.out.println("Sumario de vendas");

		for (SellerSumDTO obj : page2) {

			System.out.println(obj);


		}
	}
	}








