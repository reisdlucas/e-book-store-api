package com.book_store.e_book_store_api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_AUTOR")
@Data
@Builder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor

public class Autor {

    @Id
    @SequenceGenerator(name = "SQ_AUTOR", sequenceName = "SQ_AUTOR", allocationSize = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_AUTOR")
    @Column(name = "ID_AUTOR")
    private Long id;

    @Column(name = "NM_AUTOR", nullable = false)
    private String nome;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private Set<Livro> livro = new HashSet<>();


}
