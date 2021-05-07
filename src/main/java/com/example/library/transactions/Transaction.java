package com.example.library.transactions;

import com.example.library.books.Book;
import com.example.library.students.Student;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String transactionId= UUID.randomUUID().toString();

    private Date dateOfIssueOrReturn;

    @ManyToOne
    @JoinColumn
    private Book transactedBook;

    @ManyToOne
    @JoinColumn
    private Student studentInTransaction;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private double fine;
}
