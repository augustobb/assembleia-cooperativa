package com.cooperativa.assembleia.web.annotation;

import javax.validation.Payload;

public @interface RespostaValida {
    String message() default "Resposta inválida";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
