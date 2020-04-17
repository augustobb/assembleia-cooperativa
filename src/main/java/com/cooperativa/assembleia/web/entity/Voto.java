package com.cooperativa.assembleia.web.entity;

import com.cooperativa.assembleia.web.enums.Resposta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "seq_voto", sequenceName = "seq_voto", allocationSize = 1)
@Table(name = "voto")
public class Voto implements Serializable {
    private static final long serialVersionUID = -8040725437471100882L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_voto")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pauta_id", referencedColumnName = "id", nullable = false)
    private Pauta pauta;

    @ManyToOne
    @JoinColumn(name = "associado_id", referencedColumnName = "id", nullable = false)
    private Associado associado;

    @Enumerated(EnumType.STRING)
    @Column(name = "resposta")
    private Resposta resposta;

    public void validarHabilitacaoDoAssociado() {
        this.associado.validarHabilitacaoVoto();
    }

}
