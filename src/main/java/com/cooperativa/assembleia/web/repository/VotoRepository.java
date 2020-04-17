package com.cooperativa.assembleia.web.repository;

import com.cooperativa.assembleia.web.entity.Associado;
import com.cooperativa.assembleia.web.entity.Pauta;
import com.cooperativa.assembleia.web.entity.Voto;
import com.cooperativa.assembleia.web.enums.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    Long countByPautaAndResposta(Pauta pauta, Resposta resposta);

    Optional<Voto> findByPautaAndAssociado(Pauta pauta, Associado associado);
}
