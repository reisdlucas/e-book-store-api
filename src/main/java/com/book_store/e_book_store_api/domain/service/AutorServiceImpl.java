package com.book_store.e_book_store_api.domain.service;

import com.book_store.e_book_store_api.domain.exception.NotFoundException;
import com.book_store.e_book_store_api.domain.model.Autor;
import com.book_store.e_book_store_api.domain.repositoty.AutorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorServiceImpl implements AutorService {
    private final AutorRepository autorRepository;

    public AutorServiceImpl(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override
    public Autor create(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public Autor findById(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Autor não encontrado"));
    }

    @Override
    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    @Override
    public Autor update(Long idAutor, Autor autor) {
        Autor  autorToUpdate = findById(idAutor);
        BeanUtils.copyProperties(autor, autorToUpdate, "id", "autor");
        return autorRepository.save(autorToUpdate);
    }

    @Override
    public void delete(Long idAutor) {
        Autor autorToDelete = findById(idAutor);
        autorRepository.delete(autorToDelete);
    }

}
