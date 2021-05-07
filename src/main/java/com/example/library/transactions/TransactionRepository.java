package com.example.library.transactions;

import com.example.library.students.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

//    @Query("select s from Student s where s.name=:student_name")
//    public List<Transaction> getStudentByName(String student_name);
//
//    @Query("")
//    @Modifying
//    @Transactional
//    public void issueBookById(book_id,student_id)

    @Query("select t from Transaction t where t.studentInTransaction=(select s from Student s where s.id=:student_id)")
     public List<Transaction> getAllTransactionsOfStudent(int student_id);


}
