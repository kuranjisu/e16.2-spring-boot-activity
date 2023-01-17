package com.example.studentactivitysecurity.service;

import com.example.studentactivitysecurity.entity.Student;
import com.example.studentactivitysecurity.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(String firstName, String lastName, String email, String course, String gpa) {
        Student student = new Student();

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setCourse(course);
        student.setGpa(gpa);

        studentRepository.save(student);

        return "Details got Saved";
    }

    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public ResponseEntity<List<Student>> getStudentById(@RequestParam long id) {
        return new ResponseEntity<>(studentRepository.findById(id), HttpStatus.OK);
    }

}
