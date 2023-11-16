package com.example.LibraryManagementSystem.Exceptions;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(String message){
        super(message);
    }
}
