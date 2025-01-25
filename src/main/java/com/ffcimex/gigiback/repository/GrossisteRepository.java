package com.ffcimex.gigiback.repository;

import com.ffcimex.gigiback.entity.Grossiste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrossisteRepository extends JpaRepository<Grossiste, Long> {
}
