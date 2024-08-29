package com.book_store.e_book_store_api.api.controller;

import com.book_store.e_book_store_api.domain.service.LivroService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livro")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Livro", description = "Livro resources")
public class LivroController {

    private final LivroService livroService;


}
