package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(obj.seller.id, obj.date, obj.amount, obj.seller.name " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :minDate  AND :maxDate  "+
            "AND LOWER(obj.seller.name) LIKE '%odinson%' ")

    Page<SaleMinDTO> getReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

    Page<SaleMinDTO> getReport();
}





