package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SellerSumDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(obj.seller.id, obj.amount, obj.date, obj.seller.name) " +
            "   FROM Sale obj " +
            "   WHERE obj.date BETWEEN :minDate AND :maxDate " +
            "   AND LOWER(obj.seller.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "   ORDER BY obj.date DESC ")
    Page<SaleMinDTO> getReport(@Param("minDate") LocalDate minDate,
                               @Param("maxDate") LocalDate maxDate,
                               @Param("name") String name, Pageable pageable);


    @Query("SELECT new com.devsuperior.dsmeta.dto.SellerSumDTO(obj.seller.name, SUM(obj.amount))" +
            "   FROM Sale obj " +
            "   WHERE obj.date BETWEEN :minDate AND :maxDate " +
            "   GROUP BY obj.seller.name ")
    Page<SellerSumDTO> getSummary(@Param("minDate") LocalDate minDate,
                                  @Param("maxDate") LocalDate maxDate,
                                  Pageable pageable);


}










