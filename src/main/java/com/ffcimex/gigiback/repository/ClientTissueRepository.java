package com.ffcimex.gigiback.repository;

import com.ffcimex.gigiback.entity.ClientTissue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientTissueRepository extends JpaRepository<ClientTissue, Long> {
}
