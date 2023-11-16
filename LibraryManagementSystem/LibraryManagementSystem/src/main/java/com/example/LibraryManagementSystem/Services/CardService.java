package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Enums.CardStatus;
import com.example.LibraryManagementSystem.Exceptions.InvalidCardException;
import com.example.LibraryManagementSystem.Models.LibraryCard;
import com.example.LibraryManagementSystem.Models.Student;
import com.example.LibraryManagementSystem.Repositorys.CardRepository;
import com.example.LibraryManagementSystem.Repositorys.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService{
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private StudentRepository studentRepository;
    public LibraryCard generateCard() {
        LibraryCard card = new LibraryCard();
        card.setCardStatus(CardStatus.New);
        card = cardRepository.save(card);
        return card;
    }

    public String associateStudentWithCard(Integer studentId, Integer cardNo) throws InvalidCardException,Exception {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isEmpty()) throw new Exception("Invalid Student id");
        Student student = studentOptional.get();
        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardNo);
        if(optionalLibraryCard.isEmpty()) throw new InvalidCardException("Invalid card id");
        LibraryCard libraryCard = optionalLibraryCard.get();

        //Setting the required attributes of the libraryCard Entity
        libraryCard.setCardStatus(CardStatus.Active);
        libraryCard.setNameOnCard(student.getName());
        libraryCard.setStudent(student);
        //Setting the attribute of the student Entity
        student.setLibraryCard(libraryCard);
        studentRepository.save(student);
        return "Card with "+cardNo+" has been associated to student with "+studentId;
    }
}
