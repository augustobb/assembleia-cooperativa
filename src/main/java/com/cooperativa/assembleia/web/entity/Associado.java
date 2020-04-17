package com.cooperativa.assembleia.web.entity;

import com.cooperativa.assembleia.web.exception.BusinessException;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

import static com.cooperativa.assembleia.web.message.MessageKey.ASSOCIADO_NAO_PODE_VOTAR;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "seq_associado", sequenceName = "seq_associado", allocationSize = 1)
@Table(name = "associado")
public class Associado implements Serializable {
    private static final long serialVersionUID = -4711647541237521203L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_associado")
    private Long id;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Getter(AccessLevel.NONE)
    private transient Boolean podeVotar;

    public void validarHabilitacaoVoto() {
        if(Boolean.FALSE.equals(this.podeVotar)) {
            throw new BusinessException(ASSOCIADO_NAO_PODE_VOTAR);
        }
    }
}
