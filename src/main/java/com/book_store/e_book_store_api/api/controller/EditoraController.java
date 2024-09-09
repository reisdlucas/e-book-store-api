package com.book_store.e_book_store_api.api.controller;

import com.book_store.e_book_store_api.api.http.resources.request.EditoraRequest;
import com.book_store.e_book_store_api.api.http.resources.response.EditoraResponse;
import com.book_store.e_book_store_api.core.mapper.EditoraMapper;
import com.book_store.e_book_store_api.domain.model.Editora;
import com.book_store.e_book_store_api.domain.service.EditoraService;
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
@RequestMapping("/editora")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Editora", description = "Editora resources")
public class EditoraController {

    private final EditoraService editoraService;

    private final EditoraMapper editoraMapper;

    public EditoraController(EditoraService editoraService, EditoraMapper editoraMapper) {
        this.editoraService = editoraService;
        this.editoraMapper = editoraMapper;
    }

    @PostMapping
    @Operation(description = "Cadastrar editora", summary = "Cadastrar editora")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação bem sucedida",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EditoraResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Bad request")
    })
    public ResponseEntity<EditoraResponse> create(@RequestBody @Valid EditoraRequest editoraRequest,
                                                  UriComponentsBuilder  uriComponentsBuilder) {
        Editora editora = editoraMapper.mapRequestToEntity(editoraRequest);
        Editora createdEditora = editoraService.create(editora);

        URI uri = uriComponentsBuilder.path("/editora/{id}")
                .buildAndExpand(createdEditora.getId())
                .toUri();
        EditoraResponse editoraResponse = editoraMapper.mapEntityToResponse(createdEditora);
        return ResponseEntity.created(uri).body(editoraResponse);
    }
}
