package com.book_store.e_book_store_api.api.controller;

import com.book_store.e_book_store_api.api.http.resources.request.AutorRequest;
import com.book_store.e_book_store_api.api.http.resources.response.AutorResponse;
import com.book_store.e_book_store_api.core.mapper.AutorMapper;
import com.book_store.e_book_store_api.domain.model.Autor;
import com.book_store.e_book_store_api.domain.service.AutorService;
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

}
