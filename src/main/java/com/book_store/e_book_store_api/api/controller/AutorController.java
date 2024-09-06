package com.book_store.e_book_store_api.api.controller;

import com.book_store.e_book_store_api.api.http.resources.request.AutorRequest;
import com.book_store.e_book_store_api.api.http.resources.response.AutorResponse;
import com.book_store.e_book_store_api.core.mapper.AutorMapper;
import com.book_store.e_book_store_api.domain.model.Autor;
import com.book_store.e_book_store_api.domain.service.AutorService;
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
@RequestMapping("/autor")
@SecurityRequirement(name = "bearer Authentication")
@Tag(name = "Autor", description = "Autor resources")
public class AutorController {

    private final AutorService autorService;

    private final AutorMapper  autorMapper;

    public AutorController(AutorService autorService, AutorMapper autorMapper) {
        this.autorService = autorService;
        this.autorMapper = autorMapper;
    }

    @PostMapping
    @Operation(description = "Cadastrar autor", summary = "Cadastrar autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação bem sucedida",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Bad request")
    })
    public ResponseEntity<AutorResponse> createAutor(@RequestBody @Valid AutorRequest autorRequest,
                                                     UriComponentsBuilder uriComponentsBuilder) {
        Autor autor = autorMapper.mapRequestToEntity(autorRequest);
        Autor createdAutor = autorService.create(autor);

        URI uri = uriComponentsBuilder.path("/autor/{id}")
                .buildAndExpand(createdAutor.getId())
                .toUri();
        AutorResponse autorResponse = autorMapper.mapEntityToResponse(createdAutor);
        return ResponseEntity.created(uri).body(autorResponse);
    }

    @GetMapping
    @ResponseBody
    @Operation(description = "Buscar todos os autores", summary = "Buscar todos os autores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = AutorResponse.class)))),
    })
    public ResponseEntity<List<AutorResponse>> findAll() {
        List<Autor> autor = autorService.findAll();
        List<AutorResponse> autorResponse = autor.stream()
                .map(autorMapper::mapEntityToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(autorResponse);
    }

    @GetMapping("{idAutor}")
    @Operation(description = "Buscar autor por ID", summary = "Buscar autor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = AutorResponse.class))),
    })
    public ResponseEntity<AutorResponse> findById(@PathVariable(name = "idAutor") Long idAutor) {
        Autor autor = autorService.findById(idAutor);
        AutorResponse autorResponse = autorMapper.mapEntityToResponse(autor);
        return ResponseEntity.ok(autorResponse);
    }

    @PutMapping(value = "/{idAutor}")
    @Operation(description = "Editar autor", summary = "Editar autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AutorResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<AutorResponse> update(@Parameter(description = "ID do autor", required = true)
                                                @PathVariable(name = "idAutor") Long idAutor,
                                                @RequestBody AutorRequest autorRequest) {
        Autor autor = autorMapper.mapRequestToEntity(autorRequest);
        Autor updatedAutor = autorService.update(idAutor, autor);
        AutorResponse autorResponse = autorMapper.mapEntityToResponse(updatedAutor);
        return ResponseEntity.ok(autorResponse);
    }

}
