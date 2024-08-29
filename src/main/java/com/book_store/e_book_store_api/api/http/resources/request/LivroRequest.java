package com.book_store.e_book_store_api.api.http.resources.request;

import com.book_store.e_book_store_api.domain.model.Autor;
import com.book_store.e_book_store_api.domain.model.Editora;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroRequest {

    @NotBlank(message = "Can't be blank")
    @Schema(description = "Titulo  do Livro")
    private String titulo;

    @NotBlank(message = "Can't be blank")
    @Schema(description = "ID da editora")
    private Long idEditora;

    @NotBlank(message = "Can't be blank")
    @Schema(description = "ID do autor")
    private Long idAutor;

}
