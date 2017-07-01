/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.controller;

import com.dsltn.crud.model.Book;
import com.dsltn.crud.service.BookService;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author desolation
 */
@Controller
@RequestMapping("/")
public class BookController {
    private BookService bookService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcomePage(ModelMap model){
        return "index";    
    }
    
    public void setBookService(BookService bookService){
        this.bookService = bookService;
    }
    
   
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String bookList(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("bookList", this.bookService.getAllBooks());
        return "books";
    }
}
