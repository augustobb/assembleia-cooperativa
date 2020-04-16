package com.cooperativa.assembleia.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.cooperativa.assembleia.web.enums.Resposta.NAO;
import static com.cooperativa.assembleia.web.enums.Resposta.SIM;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resultado")
public class Resultado {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long votosSim;
    private Long votosNao;

    public String getResposta() {
        if(this.votosSim.equals(this.votosNao)) {
            return "Empate";
        }
        return this.votosSim > this.votosNao ? SIM.getValue() : NAO.getValue();
    }
}
