package com.example.library.students;

import com.example.library.books.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    @Query("select s from Student s where s.name=:student_name")
    public List<Student> getStudentByName(String student_name);
}
