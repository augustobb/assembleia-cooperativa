package com.cooperativa.assembleia.web.service.converter;

public interface Converter <E, D> {
    E toEntity (D dto);
    D toDTO (E entity);
}
