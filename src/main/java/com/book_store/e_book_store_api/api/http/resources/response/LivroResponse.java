package com.book_store.e_book_store_api.api.http.resources.response;

import com.book_store.e_book_store_api.domain.model.Autor;
import com.book_store.e_book_store_api.domain.model.Editora;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroResponse {

    @Schema(description = "Id do livro")
    private Long id;

    @Schema(description = "Titulo do livro")
    private String titulo;

    @Schema(description = "ID da editora")
    private Long idEditora;

    @Schema(description = "ID do autor")
    private Long idAutor;
}
