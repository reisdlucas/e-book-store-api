package com.book_store.e_book_store_api.domain.service;

import com.book_store.e_book_store_api.domain.model.Livro;

import java.util.List;

public interface LivroService {

    Livro create(Livro livro);

    Livro findById(Long id);

    List<Livro> findAll();

    Livro update(Long idLivro, Livro livro);

    void delete(Long idLivro);
}
