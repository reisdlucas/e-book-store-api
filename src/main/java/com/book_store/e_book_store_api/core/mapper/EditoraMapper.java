package com.book_store.e_book_store_api.core.mapper;

import com.book_store.e_book_store_api.api.http.resources.request.EditoraRequest;
import com.book_store.e_book_store_api.api.http.resources.response.EditoraResponse;
import com.book_store.e_book_store_api.domain.model.Editora;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EditoraMapper {

    @Mapping(target = "id", ignore = true)
    Editora mapRequestToEntity(EditoraRequest editoraRequest);

    @Mapping(source = "id", target = "id")
    EditoraResponse mapEntityToResponse(Editora editora);
}
