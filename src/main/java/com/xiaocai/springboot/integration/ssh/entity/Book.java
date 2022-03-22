package com.xiaocai.springboot.integration.ssh.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/14 15:47
 */
@Entity
@Table(name = "books")
@Data
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private int bookId;
    @Column(name = "title")
    private String title;
    @Column(name = "category")
    private String category;
}