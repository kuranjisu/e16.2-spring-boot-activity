package com.example.studentactivitysecurity.controller;

import com.example.studentactivitysecurity.entity.Student;
import com.example.studentactivitysecurity.repository.StudentRepository;
import com.example.studentactivitysecurity.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

@RequestMapping(path="/api")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping(path = "/student")
    public @ResponseBody String addStudent(@RequestParam String firstName,
                                           @RequestParam String lastName,
                                           @RequestParam String email,
                                           @RequestParam String course) {

        return studentService.addStudent(firstName, lastName, email, course);
    }

    @GetMapping(path = "/students")
    public @ResponseBody Iterable<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(path = "/studentid")
    public ResponseEntity<List<Student>> getStudentById(@RequestParam long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateTask(@PathVariable int id,@RequestBody Student studentDetails ){
        Student student = studentRepository.getById((long) id);

        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setCourse(studentDetails.getCourse());
        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<List<Student>> deleteStudent(@PathVariable int id){
        return new ResponseEntity<>(studentRepository.deleteByid(id), HttpStatus.OK);
    }

}
