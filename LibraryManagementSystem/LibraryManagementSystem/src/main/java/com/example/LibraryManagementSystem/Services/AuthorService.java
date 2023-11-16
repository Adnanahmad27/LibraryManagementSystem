package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Exceptions.AuthorNotFoundException;
import com.example.LibraryManagementSystem.Models.Author;
import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Repositorys.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    public String addAuthor(Author author) {
        Author author1 = authorRepository.save(author);
        return author1.getAuthorId()+" author is save to db";
    }

    public List<String> getAllAuthorNames() {
        List<Author> authorList = authorRepository.findAll();
        List<String> authorNameList = new ArrayList<>();
        for(Author author : authorList)
            authorNameList.add(author.getName());
        return authorNameList;
    }

    public Author getAuthorById(Integer id) throws AuthorNotFoundException {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if(optionalAuthor.isEmpty()){
            throw new AuthorNotFoundException("Author id is inValid");
        }
        return optionalAuthor.get();
    }

    public List<String> getBookNames(Integer authorId) {
        Author author = authorRepository.findById(authorId).get();
        List<Book> bookList = author.getBookList();
        List<String> bookNameList = new ArrayList<>();
        for(Book book : bookList)
            bookNameList.add(book.getName());
        return bookNameList;
    }
}
