package com.example.LibraryManagementSystem.Repositorys;

import com.example.LibraryManagementSystem.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
