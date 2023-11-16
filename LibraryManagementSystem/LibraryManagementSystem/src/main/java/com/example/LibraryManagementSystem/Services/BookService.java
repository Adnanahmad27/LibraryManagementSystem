package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Enums.Genre;
import com.example.LibraryManagementSystem.Exceptions.AuthorNotFoundException;
import com.example.LibraryManagementSystem.Models.Author;
import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Repositorys.AuthorRepository;
import com.example.LibraryManagementSystem.Repositorys.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService  {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    public String addBook(Book book, Integer authorId) throws AuthorNotFoundException {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if(authorOptional.isEmpty()) throw new AuthorNotFoundException("Invalid author Id");
        Author author = authorOptional.get();
        book.setAuthor(author);
        author.getBookList().add(book);

        authorRepository.save(author);
        return "Book has been added to the db";
    }
    public List<String> getBookByGenre(Genre genre){
        List<Book> bookList = bookRepository.findBooksByGenre(genre);
        List<String> bookNames = new ArrayList<>();
        for(Book book: bookList){
            bookNames.add(book.getName());
        }
        return bookNames;
    }
}
