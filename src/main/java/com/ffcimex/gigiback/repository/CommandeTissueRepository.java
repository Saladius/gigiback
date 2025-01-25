package com.ffcimex.gigiback.repository;

import com.ffcimex.gigiback.entity.CommandeTissue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeTissueRepository extends JpaRepository<CommandeTissue, Long> {
}
