package com.example.library.books;

import com.example.library.transactions.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BookController {
    Logger logger= LoggerFactory.getLogger(BookController.class);
    @Autowired
    BookService bookService;

    @PostMapping("/addBook")
    public void addBook(@RequestBody Book newBook){
        logger.debug("inside addBook function, book:{}",newBook);
        bookService.createBook(newBook);
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable("id") int id){
        return bookService.getBookById(id);
    }

//    @PutMapping("/bookissued")
//    public void updateASBookIssued(@RequestParam("book_id") int book_id){
//        bookService.markBookAsIssuedOrReturned(book_id, TransactionType.ISSUE);
//    }

//    @GetMapping("/{book_name}")
//    public List<Book> getBookByItsName(@PathVariable("book_name") String book_name){
//        return bookService.getBookByName(book_name);
//    }


}
