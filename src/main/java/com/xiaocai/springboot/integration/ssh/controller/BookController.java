package com.xiaocai.springboot.integration.ssh.controller;

import com.xiaocai.springboot.integration.ssh.entity.Book;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/14 15:55
 */
public interface BookController {

    Book getBook(int id);

    @ResponseBody
    @RequestMapping("/getAll")
    List getAll();

    @ResponseBody
    @RequestMapping("/updateBook")
    String updateBook(Book book);



    @ResponseBody
    @RequestMapping("/deleteBook")
    String deleteBook(int id);

    @ResponseBody
    @RequestMapping("/addBook")
    String addBook(Book book);

    @ResponseBody
    @RequestMapping("/bookExists")
    String bookExists(Book book);
}
