package com.example.LibraryManagementSystem.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    @Column(nullable = false)
    private String name;
    private Integer age;
    private String email;
    private String mobNo;
    // library card should be also attached
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private LibraryCard libraryCard;
}
