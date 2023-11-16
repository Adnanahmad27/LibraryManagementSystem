package com.example.LibraryManagementSystem.Controllers;

import com.example.LibraryManagementSystem.Models.LibraryCard;
import com.example.LibraryManagementSystem.Services.CardService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("card")
public class CardController {
    @Autowired
    private CardService cardService;
    @PostMapping("/generatePlainCard")
    public ResponseEntity generatePlainCard(){
        LibraryCard newCard = cardService.generateCard();
        String response = "The new card is generated and cardNo is "+newCard.getCardNo();
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @PutMapping("/associateWithStudent")
    public ResponseEntity associateWithStudent(@RequestParam("studentId")Integer studentId,@RequestParam("cardNo")Integer cardNo){
        try{
            String result = cardService.associateStudentWithCard(studentId,cardNo);
            return new ResponseEntity(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
