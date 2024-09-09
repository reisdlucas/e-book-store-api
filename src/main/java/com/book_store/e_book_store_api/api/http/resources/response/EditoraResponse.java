package com.book_store.e_book_store_api.api.http.resources.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditoraResponse {

    @Schema(description = "Id da editora")
    private Long id;

    @Schema(description = "Nome da editora")
    private String nome;
}
