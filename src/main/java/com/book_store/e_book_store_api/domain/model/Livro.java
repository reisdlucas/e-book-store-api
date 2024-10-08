package com.book_store.e_book_store_api.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_LIVRO")
@Data
@Builder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Livro {
    @Id
    @SequenceGenerator(name = "SQ_LIVRO", sequenceName = "SQ_LIVRO", allocationSize = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_LIVRO")
    @Column(name = "ID_LIVRO")
    private Long id;

    @Column(name = "TL_LIVRO", nullable = false)
    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editora_id")
    private Editora editora;

    @ManyToMany
    @JoinTable(
            name = "tb_livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private Set<Autor> autor = new HashSet<>();
}
