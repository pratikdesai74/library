package com.example.library.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
//("/student")
    @Autowired
    StudentService studentService;

    @PostMapping("/register")
    public void registerStudnet(@RequestBody Student newStudent){
        studentService.addStudent(newStudent);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable("id") int id){
        return studentService.getStudnetByItsID(id);
    }

    @GetMapping("/search/{studnet_name}")
    public List<Student> getStudentByItsName(@PathVariable("studnet_name") String studnet_name){
        return studentService.getStudentByname(studnet_name);
    }

}
