package com.book_store.e_book_store_api.core.mapper;

import com.book_store.e_book_store_api.api.http.resources.request.AutorRequest;
import com.book_store.e_book_store_api.api.http.resources.response.AutorResponse;
import com.book_store.e_book_store_api.domain.model.Autor;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AutorMapper {

    @Mapping(target = "id", ignore = true)
    Autor mapRequestToEntity(AutorRequest autorRequest);

    @Mapping(source = "id", target = "id")
    AutorResponse mapEntityToResponse(Autor autor);
}
