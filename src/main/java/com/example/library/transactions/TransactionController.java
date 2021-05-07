package com.example.library.transactions;


import com.example.library.books.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {

    Logger logger=LoggerFactory.getLogger(TransactionController.class);
    @Autowired
    TransactionService transactionService;

//    @GetMapping(value = "/issuebook" )
//    public void issueBook(@RequestParam("book_id") int book_id ,
//                            @RequestParam("student_id") int student_id){
//        logger.debug("---------------------------------------input values bookId: {} and StudentId: {}",book_id,student_id);
//        transactionService.issueBookUsingBookAndStudentId(book_id,student_id);
//    }

    @PostMapping("/issuebook" )
    public void issueBook(@RequestBody Map<String,Integer> map) {
        int book_id=map.get("book_id");
        int student_id=map.get("student_id");

        transactionService.issueBookUsingBookAndStudentId(book_id,student_id);
    }

    @PostMapping("/returnbook")
    public void returnBook(@RequestBody Map<String,Integer> map){
        int book_id=map.get("book_id");
        int student_id=map.get("student_id");

        transactionService.returnBookUsingBookAndStudentId(book_id,student_id);
    }

    @GetMapping(value = "/transaction_details", produces = MediaType.TEXT_PLAIN_VALUE) //produces ="text/csv")
    public void generateTransactionFile(@RequestParam("student_id") int student_id, HttpServletResponse response)throws IOException {

        List<Transaction> list= transactionService.getAllTransactions(student_id);
        transactionService.writeTransactions(response.getWriter(), list);
    }

}
