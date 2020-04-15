package com.cooperativa.assembleia.web.validation;

import com.cooperativa.assembleia.web.annotation.RespostaValida;
import com.cooperativa.assembleia.web.enums.Resposta;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class RespostaValidator implements ConstraintValidator<RespostaValida, String> {

    private List<String> respostasPossiveis;

    @Override
    public void initialize(RespostaValida constraintAnnotation) {
        respostasPossiveis = new ArrayList<>();
        for(Resposta val : Resposta.values()) {
            respostasPossiveis.add(val.getValue().toUpperCase());
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return respostasPossiveis.contains(value.toUpperCase());
    }
}
