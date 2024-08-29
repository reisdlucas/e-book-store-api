package com.book_store.e_book_store_api.api.controller;

import com.book_store.e_book_store_api.api.http.resources.request.LivroRequest;
import com.book_store.e_book_store_api.api.http.resources.response.LivroResponse;
import com.book_store.e_book_store_api.core.mapper.LivroMapper;
import com.book_store.e_book_store_api.domain.model.Livro;
import com.book_store.e_book_store_api.domain.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/livro")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Livro", description = "Livro resources")
public class LivroController {

    private final LivroService livroService;

    private final LivroMapper livroMapper;

    public LivroController(LivroService livroService, LivroMapper livroMapper) {
        this.livroService = livroService;
        this.livroMapper = livroMapper;
    }

    @PostMapping
    @Operation(description = "Cadastrar livro", summary = "Cadastrar livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação bem sucedida ",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LivroResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Bad request")
    })
    public ResponseEntity<LivroResponse> createLivro(@RequestBody @Valid LivroRequest livroRequest,
                                                     UriComponentsBuilder uriComponentsBuilder) {
        Livro livro = livroMapper.mapRequestToEntity(livroRequest);
        Livro createdLivro = livroService.create(livro);

        URI uri = uriComponentsBuilder.path("/livro/{id}")
                .buildAndExpand(createdLivro.getId())
                .toUri();
        LivroResponse livroResponse = livroMapper.mapEntityToResponse(createdLivro);
        return ResponseEntity.created(uri).body(livroResponse);
    }

}
