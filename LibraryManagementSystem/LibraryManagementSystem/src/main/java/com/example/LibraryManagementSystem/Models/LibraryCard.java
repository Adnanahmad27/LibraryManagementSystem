package com.example.LibraryManagementSystem.Models;

import com.example.LibraryManagementSystem.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "library_card")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardNo;
    private String nameOnCard;
    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;
    private Integer noOfBookedIssued;
    @OneToOne
    @JoinColumn
    private Student student;//This is acting as a FK of the Library Card table
    //This variable is to be put in mappedBy attribute in the parent class
    @OneToMany(mappedBy = "card" , cascade = CascadeType.ALL)
    private List<Transactions> transactionList = new ArrayList<>();

}
