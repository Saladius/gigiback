package com.ffcimex.gigiback.repository;

import com.ffcimex.gigiback.entity.TrancheAge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrancheAgeRepository extends JpaRepository<TrancheAge, Long> {
}
