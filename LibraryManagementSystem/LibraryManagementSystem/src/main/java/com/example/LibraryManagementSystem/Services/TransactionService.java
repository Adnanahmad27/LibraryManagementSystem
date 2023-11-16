package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Enums.CardStatus;
import com.example.LibraryManagementSystem.Enums.TransactionStatus;
import com.example.LibraryManagementSystem.Exceptions.BookNotFoundException;
import com.example.LibraryManagementSystem.Exceptions.CardNotFoundException;
import com.example.LibraryManagementSystem.Exceptions.InvalidCardException;
import com.example.LibraryManagementSystem.Exceptions.MaxLimitExceedException;
import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Models.LibraryCard;
import com.example.LibraryManagementSystem.Models.Transactions;
import com.example.LibraryManagementSystem.Repositorys.BookRepository;
import com.example.LibraryManagementSystem.Repositorys.CardRepository;
import com.example.LibraryManagementSystem.Repositorys.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    private static final Integer MAX_LIMIT_OF_BOOKS = 3;
    private static final Integer FINE_PER_DAY = 5;

    public String issueBook(Integer bookId, Integer cardId) throws Exception {
        Transactions transaction = new Transactions();
        transaction.setTransactionStatus(TransactionStatus.PENDING);

        //Validation
        //valid bookId
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isEmpty()) throw new BookNotFoundException("BookId is invalid");
        Book book = bookOptional.get();

        //Availability of book
        if(!book.isAvailable()) throw new Exception("Book is Unavailable");

        // Valid cardId
        Optional<LibraryCard> libraryCardOptional = cardRepository.findById(cardId);
        if(libraryCardOptional.isEmpty()) throw new CardNotFoundException("CardId entered is Invalid");
        LibraryCard libraryCard = libraryCardOptional.get();

        //Valid card Status
        if(!libraryCard.getCardStatus().equals(CardStatus.Active)) throw new InvalidCardException("Card Status is not Active");

        //Maximum no of book Issues
        if(libraryCard.getNoOfBookedIssued()==MAX_LIMIT_OF_BOOKS){
            throw new MaxLimitExceedException(MAX_LIMIT_OF_BOOKS+" is max book that can be issued");
        }

        transaction.setTransactionStatus(TransactionStatus.ISSUED);
        libraryCard.setNoOfBookedIssued(libraryCard.getNoOfBookedIssued()+1);
        book.setAvailable(false);

        // setting FK
        transaction.setBook(book);
        transaction.setCard(libraryCard);

        book.getTransactionList().add(transaction);
        libraryCard.getTransactionList().add(transaction);

        transactionRepository.save(transaction);
        return "The book with bookId "+bookId+" has been issued to card with "+cardId;
    }

    public String returnBook(Integer bookId, Integer cardId) {
        Book book = bookRepository.findById(bookId).get();
        LibraryCard card = cardRepository.findById(cardId).get();

        //I need to find out that issue transaction
        Transactions transaction = transactionRepository.findTransactionByBookAndCardAndTransactionStatus(book,card,TransactionStatus.ISSUED);
        Date issueDate = transaction.getCreatedOn();
        long milliSeconds = Math.abs(System.currentTimeMillis()-issueDate.getTime());
        Long days = TimeUnit.DAYS.convert(milliSeconds,TimeUnit.MILLISECONDS);
        int fine = 0;
        if(days > 15) fine = Math.toIntExact(days-15)*FINE_PER_DAY;

        transaction.setTransactionStatus(TransactionStatus.COMPLETED);
        book.setAvailable(true);
        card.setNoOfBookedIssued(card.getNoOfBookedIssued()-1);
        transaction.setFine(fine);
        transaction.setReturnDate(new Date());

        transactionRepository.save(transaction);
        return "Book has been returned";

    }
}
