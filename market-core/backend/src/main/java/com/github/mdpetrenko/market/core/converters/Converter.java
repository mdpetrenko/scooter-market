package com.github.mdpetrenko.market.core.converters;

public interface Converter<D,E> {

    D entityToDto(E entity);
    E dtoToEntity(D dto);
}
