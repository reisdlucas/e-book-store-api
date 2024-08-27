package com.book_store.e_book_store_api.domain.repositoty;

import com.book_store.e_book_store_api.domain.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
}
