package com.cooperativa.assembleia.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pauta")
public class PautaEntity implements Serializable {

    private static final long serialVersionUID = -545499144683315996L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pergunta", nullable = false)
    private String pergunta;

    @Column(name = "segundos_duracao")
    private Long segundosDuracao;

    @Column(name = "criacao")
    private LocalDateTime criacao;
}
