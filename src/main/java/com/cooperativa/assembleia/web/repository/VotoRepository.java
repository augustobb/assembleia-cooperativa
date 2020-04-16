package com.cooperativa.assembleia.web.repository;

import com.cooperativa.assembleia.web.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> {
}
