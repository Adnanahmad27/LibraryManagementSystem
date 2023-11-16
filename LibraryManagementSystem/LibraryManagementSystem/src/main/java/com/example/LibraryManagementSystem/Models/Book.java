package com.example.LibraryManagementSystem.Models;

import com.example.LibraryManagementSystem.Enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    private Integer noOfPages;
    private double rating;
    private boolean isAvailable;
    @ManyToOne
    @JoinColumn
    private Author author;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Transactions> transactionList = new ArrayList<>();


}
