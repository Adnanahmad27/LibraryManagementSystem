package com.example.LibraryManagementSystem.Controllers;

import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("addBook")
    public ResponseEntity addBook(@RequestBody Book book, @RequestParam("authorId")Integer authorId){
        try{
            String result = bookService.addBook(book,authorId);
            return new ResponseEntity(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }
}
