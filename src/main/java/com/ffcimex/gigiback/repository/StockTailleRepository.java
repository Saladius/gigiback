package com.ffcimex.gigiback.repository;

import com.ffcimex.gigiback.entity.StockTaille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockTailleRepository extends JpaRepository<StockTaille, Long> {
}
