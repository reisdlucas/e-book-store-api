package com.book_store.e_book_store_api.domain.service;

import com.book_store.e_book_store_api.domain.exception.NotFoundException;
import com.book_store.e_book_store_api.domain.model.Editora;
import com.book_store.e_book_store_api.domain.repositoty.EditoraRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditoraServiceImpl implements EditoraService {

    private final EditoraRepository editoraRepository;

    public EditoraServiceImpl(EditoraRepository editoraRepository) {
        this.editoraRepository = editoraRepository;
    }

    @Override
    public Editora create(Editora editora) {
        return editoraRepository.save(editora);
    }

    @Override
    public Editora findById(Long id) {
        return editoraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Editora não encontrada"));
    }

    @Override
    public List<Editora> findAll() {
        return editoraRepository.findAll();
    }

    @Override
    public Editora update(Long idEditora, Editora editora) {
        Editora editoraToUpdate = findById(idEditora);
        BeanUtils.copyProperties(editora, editoraToUpdate, "id", "editora");
        return editoraRepository.save(editoraToUpdate);
    }

    @Override
    public void delete(Long idEditora) {
        Editora editoraToDelete = findById(idEditora);
        editoraRepository.delete(editoraToDelete);
    }
}
