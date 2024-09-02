package com.book_store.e_book_store_api.api.controller;

import com.book_store.e_book_store_api.api.http.resources.request.LivroRequest;
import com.book_store.e_book_store_api.api.http.resources.response.LivroResponse;
import com.book_store.e_book_store_api.core.mapper.LivroMapper;
import com.book_store.e_book_store_api.domain.model.Livro;
import com.book_store.e_book_store_api.domain.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
            @ApiResponse(responseCode = "201", description = "Operação bem sucedida",
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

    @GetMapping
    @ResponseBody
    @Operation(description = "Buscar todos os livros", summary = "Buscar todos os livros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = LivroResponse.class)))),
    })
    public ResponseEntity<List<LivroResponse>> findAll() {
        List<Livro> livro = livroService.findAll();
        List<LivroResponse> livroResponse = livro.stream()
                .map(livroMapper::mapEntityToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(livroResponse);
    }

    @GetMapping("/{idLivro}")
    @Operation(description = "Buscar livro po ID", summary = "Buscar livro por ID")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LivroResponse.class))),
    })
    public ResponseEntity<LivroResponse> findById(@PathVariable(name = "idLivro") Long idLivro) {
        Livro livro = livroService.findById(idLivro);
        LivroResponse livroResponse = livroMapper.mapEntityToResponse(livro);
        return ResponseEntity.ok(livroResponse);
    }

    @PutMapping(value = "/{idLivro}")
    @Operation(description = "Editar livro", summary = "Editar livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucesida",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LivroResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<LivroResponse> update(@Parameter(description = "ID do livro", required = true)
                                                @PathVariable(name = "idLivro") Long idLivro,
                                                @RequestBody LivroRequest livroRequest) {
        Livro livro = livroMapper.mapRequestToEntity(livroRequest);
        Livro updatedLivro = livroService.update(idLivro, livro);
        LivroResponse livroResponse = livroMapper.mapEntityToResponse(updatedLivro);
        return ResponseEntity.ok(livroResponse);
    }

}
