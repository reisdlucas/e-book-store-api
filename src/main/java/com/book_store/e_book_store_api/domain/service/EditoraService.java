package com.book_store.e_book_store_api.domain.service;

import com.book_store.e_book_store_api.domain.model.Editora;

import java.util.List;

public interface EditoraService {

    Editora create(Editora editora);

    Editora findById(Long id);

    List<Editora> findAll();

    Editora update(Long idEditora, Editora editora);

    void delete(Long idEditora);
}
