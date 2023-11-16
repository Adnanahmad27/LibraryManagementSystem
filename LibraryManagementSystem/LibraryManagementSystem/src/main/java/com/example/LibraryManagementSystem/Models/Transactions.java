package com.example.LibraryManagementSystem.Models;

import com.example.LibraryManagementSystem.Enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;
    private Date returnDate;
    private Integer fine;

    @CreationTimestamp
    private Date createdOn;//Handled by Spring internally
    @UpdateTimestamp
    private Date lastModifiedOn;

    //connect FK here with Book Entity
    @ManyToOne
    @JoinColumn
    private Book book;
    @ManyToOne
    @JoinColumn
    private LibraryCard card;

}
