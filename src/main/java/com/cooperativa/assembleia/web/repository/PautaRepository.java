package com.cooperativa.assembleia.web.repository;

import com.cooperativa.assembleia.web.entity.PautaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PautaRepository extends JpaRepository<PautaEntity, Long> {
    Optional<PautaEntity> findFirstByOrderByCriacaoDesc();
}
