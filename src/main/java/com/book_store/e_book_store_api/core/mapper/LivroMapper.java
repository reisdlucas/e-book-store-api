package com.book_store.e_book_store_api.core.mapper;

import com.book_store.e_book_store_api.api.http.resources.request.LivroRequest;
import com.book_store.e_book_store_api.api.http.resources.response.LivroResponse;
import com.book_store.e_book_store_api.domain.model.Livro;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface LivroMapper {

    @Mapping(target = "id", ignore = true)
    Livro mapRequestToEntity(LivroRequest livroRequest);

    @Mapping(source = "id", target = "id")
    LivroResponse mapEntityToResponse(Livro livro);

}
