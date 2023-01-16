package com.example.studentactivitysecurity.repository;

import com.example.studentactivitysecurity.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findById(long id);
    List<Student> deleteByid(int id);
}