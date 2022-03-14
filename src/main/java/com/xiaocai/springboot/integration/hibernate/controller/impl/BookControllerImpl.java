package com.xiaocai.springboot.integration.hibernate.controller.impl;

import com.xiaocai.springboot.integration.hibernate.controller.BookController;
import com.xiaocai.springboot.integration.hibernate.entity.Book;
import com.xiaocai.springboot.integration.hibernate.repository.BoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/14 15:56
 */
@Controller
@RequestMapping("/book")
public class BookControllerImpl implements BookController {

    @Autowired
    private BoolRepository  boolRepository;

    @ResponseBody
    @Override
    @RequestMapping("/getBookById")
    public Book getBook(@RequestParam int id) {
        Book book = boolRepository.getBookById(id);
        return book;
    }

    @ResponseBody
    @Override
    @RequestMapping("/getAll")
    public List getAll() {
        List<Book> books = boolRepository.getAllBooks();
        return books;
    }

    @ResponseBody
    @Override
    @RequestMapping("/updateBook")
    public String updateBook(Book book) {
         boolRepository.updateBook(book);
        return "更新成功";
    }

    @ResponseBody
    @Override
    @RequestMapping("/deleteBook")
    public String deleteBook(int id) {
        boolRepository.deleteBook(id);
        return "删除成功";
    }

    @ResponseBody
    @Override
    @RequestMapping("/addBook")
    public String addBook(Book book) {
        boolRepository.addBook(book);
        return "插入成功";
    }

    @ResponseBody
    @Override
    @RequestMapping("/bookExists")
    public String bookExists(Book book) {
        String category = book.getCategory();
        String title = book.getTitle();

        boolRepository.bookExists(title,category);
        return "插入成功";
    }

}
