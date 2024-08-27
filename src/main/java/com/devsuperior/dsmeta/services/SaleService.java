package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;


	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	public Page<SaleMinDTO> getReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable) {

		Page<SaleMinDTO> page = repository.getReport(minDate, maxDate, name, pageable);
        Sale entity = (Sale) page.get();
        return (Page<SaleMinDTO>) new SaleMinDTO(entity);
	}


}



