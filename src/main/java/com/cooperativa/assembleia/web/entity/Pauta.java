package com.cooperativa.assembleia.web.entity;

import com.cooperativa.assembleia.web.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import static com.cooperativa.assembleia.web.message.MessageKey.SESSAO_PAUTA_ENCERRADA;
import static java.util.Objects.nonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pauta")
public class Pauta implements Serializable {
    private static final long serialVersionUID = -545499144683315996L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pergunta", nullable = false)
    private String pergunta;

    @OneToOne
    @JoinColumn(name = "resultado_id", referencedColumnName = "id")
    private Resultado resultado;

    private boolean isEncerrada() {
        return nonNull(resultado);
    }

    public void validarPautaAberta() {
        if(isEncerrada()) {
            throw new BusinessException(SESSAO_PAUTA_ENCERRADA);
        }
    }

}
