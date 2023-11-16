package com.example.LibraryManagementSystem.Repositorys;

import com.example.LibraryManagementSystem.Enums.Genre;
import com.example.LibraryManagementSystem.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findBooksByGenre(Genre genre);
    Book findBookByName(String bookName);
}
