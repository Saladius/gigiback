package com.ffcimex.gigiback.repository;

import com.ffcimex.gigiback.entity.CommandeGrossiste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeGrossisteRepository extends JpaRepository<CommandeGrossiste, Long> {
}
