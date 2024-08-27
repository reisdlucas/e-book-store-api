package com.book_store.e_book_store_api.domain.model;

import jakarta.persistence.*;
import lombok.*;

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


}
