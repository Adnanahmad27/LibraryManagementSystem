package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Models.Student;
import com.example.LibraryManagementSystem.Repositorys.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JavaMailSender mailSender;
    public String addStudent(Student student) {
        Student student1 = studentRepository.save(student);


         SimpleMailMessage mailMessage = new SimpleMailMessage();

        String body = "Hi "+student.getName()+" !" +
                "You have successfully registered. You can start issuing the books now.";

        mailMessage.setFrom("azmi50adnan@gmail.com");
        mailMessage.setTo(student.getEmail());
        mailMessage.setSubject("Welcome To SSC School's Library !!");
        mailMessage.setText(body);
        mailSender.send(mailMessage);

        return "Student of id "+student1.getStudentId()+ " is save to db";
    }
}
