package com.book_store.e_book_store_api.domain.service;

import com.book_store.e_book_store_api.domain.model.Autor;

import java.util.List;

public interface AutorService {

    Autor create(Autor autor);

    Autor findById(Long id);

    List<Autor> findAll();

    Autor update(Long idAutor, Autor autor);

    void delete(Long idAutor);
}
