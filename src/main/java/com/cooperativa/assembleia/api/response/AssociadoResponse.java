package com.cooperativa.assembleia.api.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AssociadoResponse implements Serializable {
    private static final long serialVersionUID = 6515353558652080805L;

    private Long id;
    private String cpf;
}
