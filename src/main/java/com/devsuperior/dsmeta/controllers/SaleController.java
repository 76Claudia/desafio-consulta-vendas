package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SellerSumDTO;
import com.devsuperior.dsmeta.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;


	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinDTO>> getReport(
			@RequestParam(value = "minDate", required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String minDate,
			@RequestParam(value = "maxDate", required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String maxDate,
			@RequestParam(value = "name", defaultValue = "") String name,
			Pageable pageable) {
		Page<SaleMinDTO> dto = service.getReport(minDate, maxDate, name, pageable);
        return ResponseEntity.ok(dto);
    }
	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SellerSumDTO>> getSummary(
			@RequestParam(value = "minDate", required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String minDate,
			@RequestParam(value = "maxDate", required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String maxDate,
			Pageable pageable) {
		Page<SellerSumDTO> dto = service.getSummary(minDate, maxDate, pageable);
		return ResponseEntity.ok(dto);
	}

	}





