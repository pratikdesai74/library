package com.example.library.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public void addStudent(Student newStudent){
        studentRepository.save(newStudent);
    }

    public Student getStudnetByItsID(int studentId){
        return studentRepository.findById(studentId).orElse(null);
    }

    public List<Student> getStudentByname(String studnet_name){
        return studentRepository.getStudentByName(studnet_name);
    }
}
