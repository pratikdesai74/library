package com.example.library.students;

import com.example.library.books.Book;
import com.example.library.transactions.Transaction;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString(exclude = {"holdingBooks","transactions"})
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private String email;

    @OneToMany(mappedBy = "book_holding_student" ,cascade = CascadeType.ALL)
    private List<Book> holdingBooks;

    @OneToMany(mappedBy = "studentInTransaction",cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
