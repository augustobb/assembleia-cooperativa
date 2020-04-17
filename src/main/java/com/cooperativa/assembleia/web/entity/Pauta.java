package com.cooperativa.assembleia.web.entity;

import com.cooperativa.assembleia.web.exception.BusinessException;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

import static com.cooperativa.assembleia.web.message.MessageKey.*;
import static java.util.Objects.nonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "seq_pauta", sequenceName = "seq_pauta", allocationSize = 1)
@Table(name = "pauta")
public class Pauta implements Serializable {
    private static final long serialVersionUID = -545499144683315996L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pauta")
    private Long id;

    @Column(name = "pergunta", nullable = false)
    private String pergunta;

    @OneToOne
    @JoinColumn(name = "resultado_id", referencedColumnName = "id")
    private Resultado resultado;

    private boolean isEncerrada() {
        return nonNull(resultado);
    }

    @Getter(AccessLevel.NONE)
    private transient Boolean hasSessao;

    public void validarPautaAberta() {
        if(!Boolean.TRUE.equals(this.hasSessao)) {
            throw new BusinessException(PAUTA_SEM_SESSAO);
        }
        if(isEncerrada()) {
            throw new BusinessException(SESSAO_PAUTA_ENCERRADA);
        }
    }

    public void validarSemSessaoExistente() {
        if(Boolean.TRUE.equals(this.hasSessao)){
            throw new BusinessException(SESSAO_JA_ABERTA_PARA_PAUTA, id);
        }
    }

}
