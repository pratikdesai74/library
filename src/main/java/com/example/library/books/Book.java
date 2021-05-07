package com.example.library.books;

import com.example.library.students.Student;
import com.example.library.transactions.Transaction;
import com.example.library.transactions.TransactionType;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = {"isIssuedOrReturned","book_holding_student","transactions","lastTransactionDate"})
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String author;

    @Enumerated(EnumType.STRING)
    private TransactionType isIssuedOrReturned;
    private Date lastTransactionDate;

    @Enumerated(EnumType.STRING)
    private Genre genre;
    private double cost;

    @ManyToOne
    @JoinColumn
    private Student book_holding_student;

    @OneToMany(mappedBy = "transactedBook", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
