package com.book_store.e_book_store_api.domain.repositoty;

import com.book_store.e_book_store_api.domain.model.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {
}
