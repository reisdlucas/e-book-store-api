package com.book_store.e_book_store_api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_EDITORA")
@Data
@Builder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Editora {

    @Id
    @SequenceGenerator(name = "SQ_EDITORA", sequenceName = "SQ_EDITORA", allocationSize = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_EDITORA")
    @Column(name = "ID_EDITORA")
    private Long id;

    @Column(name = "NM_EDITORA", nullable = false)
    private String nome;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "editora", fetch = FetchType.LAZY)
    private Set<Livro> livros = new HashSet<>();

}
