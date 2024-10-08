package com.book_store.e_book_store_api.api.http.resources.request;

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
public class AutorRequest {

    @NotBlank(message = "Can't be blank")
    @Schema(description = "Nome do autor")
    private String nome;

}
