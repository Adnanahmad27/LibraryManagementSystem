package com.example.LibraryManagementSystem.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;
    private String name;
    private double rating;
    @OneToMany(mappedBy = "author" , cascade = CascadeType.ALL)
    private List<Book> bookList;

}
