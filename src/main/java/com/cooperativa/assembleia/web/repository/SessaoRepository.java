package com.cooperativa.assembleia.web.repository;

import com.cooperativa.assembleia.web.entity.Pauta;
import com.cooperativa.assembleia.web.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    Optional<Sessao> findByPauta(Pauta pauta);
}
