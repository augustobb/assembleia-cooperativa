package com.cooperativa.assembleia.web.entity;

import com.cooperativa.assembleia.web.task.EncerramentoSessaoTask;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sessao")
public class Sessao implements Serializable {
    private static final long serialVersionUID = -8052782366191911358L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pauta_id", referencedColumnName = "id", nullable = false)
    private Pauta pauta;

    @Column(name = "segundos_duracao", nullable = false)
    private Long segundosDuracao;

    @Column(name = "data_abertura", nullable = false)
    private LocalDateTime dataAbertura;

    @Getter(AccessLevel.NONE)
    @Column(name = "encerrada", nullable = false)
    private Boolean encerrada = false;

    public boolean isEncerrada() {
        return this.encerrada;
    }

    public void agendarEncerramento() {
        EncerramentoSessaoTask encerramento = new EncerramentoSessaoTask(this);
        new java.util.Timer().schedule(encerramento, this.getSegundosDuracao()*1000);
    }
}
