package com.devsuperior_springboot.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

}
