package com.ffcimex.gigiback.repository;

import com.ffcimex.gigiback.entity.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {
}
