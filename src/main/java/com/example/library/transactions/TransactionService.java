package com.example.library.transactions;

import com.example.library.books.Book;
import com.example.library.books.BookService;
import com.example.library.students.Student;
import com.example.library.students.StudentService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    BookService bookService;

    public void issueBookUsingBookAndStudentId(int book_id,int student_id){
            Student std=studentService.getStudnetByItsID(student_id);
            Date currDate=Calendar.getInstance().getTime();

            bookService.markBookAsIssuedOrReturned(book_id,TransactionType.ISSUE);
            Book book=bookService.getBookById(book_id);

            Transaction transaction=Transaction.builder().
                                    transactedBook(book).
                                    studentInTransaction(std).
                                    transactionType(TransactionType.ISSUE).
                                    dateOfIssueOrReturn(currDate).
                                    fine(0.0).
                                    build();
            transactionRepository.save(transaction);

    }

    public void returnBookUsingBookAndStudentId(int book_id,int student_id){
        Student std=studentService.getStudnetByItsID(student_id);
        Book book=bookService.getBookById(book_id);
        Date currDate=Calendar.getInstance().getTime();
        double updatedFine=0.0;
        if(currDate.compareTo(book.getLastTransactionDate()) >7){
            updatedFine=60.0;
        }

        Transaction transaction=Transaction.builder().
                transactedBook(book).
                studentInTransaction(std).
                transactionType(TransactionType.RETURN).
                dateOfIssueOrReturn(currDate).
                fine(updatedFine).
                build();
        transactionRepository.save(transaction);
        bookService.markBookAsIssuedOrReturned(book_id,TransactionType.RETURN);

    }

    public List<Transaction> getAllTransactions(int student_id) {
        return transactionRepository.getAllTransactionsOfStudent(student_id);
    }

    public static void writeTransactions(PrintWriter writer, List<Transaction> transactions) {

        try {

            ColumnPositionMappingStrategy<Transaction> mapStrategy
                    = new ColumnPositionMappingStrategy<>();

            mapStrategy.setType(Transaction.class);

            String[] columns = new String[]{"id", "transactedBook","transactionType","fine"};
            mapStrategy.setColumnMapping(columns);

            StatefulBeanToCsv<Transaction> btcsv = new StatefulBeanToCsvBuilder<Transaction>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(mapStrategy)
                    .withSeparator(',')
                    .build();

            btcsv.write(transactions);

        } catch (CsvException ex) {

            //LOGGER.error("Error mapping Bean to CSV", ex);
        }
    }

}
