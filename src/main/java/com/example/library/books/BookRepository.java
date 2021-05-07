package com.example.library.books;

import com.example.library.transactions.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query("select b from Book b where b.name=:book_name")
    public List<Book> getBookByName(String book_name);

    @Modifying
    @Query("Update Book b Set b.isIssuedOrReturned=:transactionType ,b.lastTransactionDate=:date where b.id=:book_id")
    public void markBookAsIssuedOrReturned(int book_id, TransactionType transactionType, Date date);
}
