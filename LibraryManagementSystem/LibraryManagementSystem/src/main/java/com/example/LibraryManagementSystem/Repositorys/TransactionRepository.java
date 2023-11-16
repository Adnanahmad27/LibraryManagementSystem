package com.example.LibraryManagementSystem.Repositorys;

import com.example.LibraryManagementSystem.Enums.TransactionStatus;
import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Models.LibraryCard;
import com.example.LibraryManagementSystem.Models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions,Integer> {
    Transactions findTransactionByBookAndCardAndTransactionStatus(Book book, LibraryCard card, TransactionStatus transactionStatus);
}
