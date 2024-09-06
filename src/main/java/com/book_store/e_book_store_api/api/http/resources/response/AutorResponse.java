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
public class AutorResponse {

    @Schema(description = "Id do autor")
    private Long id;

    @Schema(description = "Nome do autor")
    private String nome;

}
