package com.cooperativa.assembleia.web.service.converter;

import java.util.function.Function;

public abstract class Converter<Q, E, P> {

    private final Function<Q, E> toEntity;
    private final Function<E, P> toResponse;

    public Converter(Function<Q, E> toEntity, Function<E, P> toResponse) {
        this.toEntity = toEntity;
        this.toResponse = toResponse;
    }

    public E toEntity(Q request) {
        return toEntity.apply(request);
    }

    public P toResponse(E entity) {
        return toResponse.apply(entity);
    }
}
