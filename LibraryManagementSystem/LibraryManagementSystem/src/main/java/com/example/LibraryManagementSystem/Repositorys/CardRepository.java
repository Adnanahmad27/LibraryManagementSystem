package com.example.LibraryManagementSystem.Repositorys;

import com.example.LibraryManagementSystem.Models.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<LibraryCard,Integer> {
}
