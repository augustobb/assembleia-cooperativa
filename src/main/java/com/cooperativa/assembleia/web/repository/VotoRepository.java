package com.cooperativa.assembleia.web.repository;

import com.cooperativa.assembleia.web.entity.Pauta;
import com.cooperativa.assembleia.web.entity.Voto;
import com.cooperativa.assembleia.web.enums.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    Long countByPautaAndResposta(Pauta pauta, Resposta resposta);
}
