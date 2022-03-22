package com.xiaocai.springboot.integration.ssh.repository;

import com.xiaocai.springboot.integration.ssh.entity.Book;

import java.util.List;

public interface BoolRepository {
    List<Book> getAllBooks();

    Book getBookById(int bookId);

    void addBook(Book book);

    void updateBook(Book book);

    void deleteBook(int bookId);

    boolean bookExists(String title,String category);
}
