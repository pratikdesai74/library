package com.example.library.books;

import com.example.library.transactions.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public void createBook(Book book){
        bookRepository.save(book);
    }

    public Book getBookById(int bookId){
        return bookRepository.findById(bookId).orElse(null);
    }

    public List<Book> getBookByName(String book_name){
        return bookRepository.getBookByName(book_name);
    }

    public void markBookAsIssuedOrReturned(int book_id, TransactionType transactionType){
        bookRepository.markBookAsIssuedOrReturned(book_id,transactionType, Calendar.getInstance().getTime());
    }
}
