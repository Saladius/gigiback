package com.ffcimex.gigiback.repository;

import com.ffcimex.gigiback.entity.Tissue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TissueRepository extends JpaRepository<Tissue, Long> {
    Optional<Tissue> findByCode(String code);
}
