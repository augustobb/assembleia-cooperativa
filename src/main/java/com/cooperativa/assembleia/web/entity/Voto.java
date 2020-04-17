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
@Table(name = "voto")
public class Voto implements Serializable {
    private static final long serialVersionUID = -8040725437471100882L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
