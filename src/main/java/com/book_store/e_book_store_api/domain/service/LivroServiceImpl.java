package com.book_store.e_book_store_api.domain.service;

import com.book_store.e_book_store_api.domain.exception.NotFoundException;
import com.book_store.e_book_store_api.domain.model.Livro;
import com.book_store.e_book_store_api.domain.repositoty.LivroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroServiceImpl implements LivroService {
    private final LivroRepository livroRepository;

    public LivroServiceImpl(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Override
    public Livro create(Livro livro) {
        return livroRepository.save(livro);
    }

    @Override
    public Livro findById(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Livro não encontrado"));
    }

    @Override
    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    @Override
    public Livro update(Long idLivro, Livro livro) {
        Livro livroToUpdate = findById(idLivro);
        BeanUtils.copyProperties(livro, livroToUpdate, "id", "livro");
        return livroRepository.save(livroToUpdate);
    }

    @Override
    public void delete(Long idLivro) {
        Livro livro = findById(idLivro);
        livroRepository.delete(livro);
    }

}
