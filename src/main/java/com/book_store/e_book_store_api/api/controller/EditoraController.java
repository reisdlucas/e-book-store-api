package com.book_store.e_book_store_api.api.controller;

import com.book_store.e_book_store_api.api.http.resources.request.EditoraRequest;
import com.book_store.e_book_store_api.api.http.resources.response.EditoraResponse;
import com.book_store.e_book_store_api.core.mapper.EditoraMapper;
import com.book_store.e_book_store_api.domain.model.Editora;
import com.book_store.e_book_store_api.domain.service.EditoraService;
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

    @GetMapping
    @ResponseBody
    @Operation(description = "Buscar todas as editoras", summary = "Buscar todas as editoras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = EditoraResponse.class)))),
    })
    public ResponseEntity<List<EditoraResponse>> findAll() {
        List<Editora> editora = editoraService.findAll();
        List<EditoraResponse> editoraResponse = editora.stream()
                .map(editoraMapper::mapEntityToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(editoraResponse);
    }

    @GetMapping("/{idEditora}")
    @Operation(description = "Buscar editora por ID", summary = "Buscar editora por ID")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EditoraResponse.class))),
    })
    public ResponseEntity<EditoraResponse> findById(@PathVariable(name = "idEditora") Long idEditora) {
        Editora editora = editoraService.findById(idEditora);
        EditoraResponse editoraResponse = editoraMapper.mapEntityToResponse(editora);
        return ResponseEntity.ok(editoraResponse);
    }

    @PutMapping(value = "/{idEditora}")
    @Operation(description = "Editar editora", summary = "Editar editora")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucesida",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EditoraResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<EditoraResponse> update(@Parameter(description = "ID da editora", required = true)
                                                  @PathVariable(name = "idEditora") Long idEditora,
                                                  @RequestBody EditoraRequest editoraRequest) {
        Editora editora = editoraMapper.mapRequestToEntity(editoraRequest);
        Editora updatedEditora = editoraService.update(idEditora, editora);
        EditoraResponse editoraResponse = editoraMapper.mapEntityToResponse(updatedEditora);
        return ResponseEntity.ok(editoraResponse);
    }

    @DeleteMapping("/{idEditora}")
    @Operation(description = "Deletar editora", summary = "Deletar editora")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação bem sucesida"),
            @ApiResponse(responseCode = "404", description = "Bad request")
    })
    public ResponseEntity<List<EditoraResponse>> delete(@Parameter(description = "ID da editora", required = true)
                                                        @PathVariable(name = "idEditora") Long idEditora) {
        editoraService.delete(idEditora);
        return ResponseEntity.noContent().build();
    }

}
