/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.controller;

import com.dsltn.crud.model.Author;
import com.dsltn.crud.model.Book;
import com.dsltn.crud.model.Client;
import com.dsltn.crud.model.Genre;
import com.dsltn.crud.service.AuthorService;
import com.dsltn.crud.service.BookService;
import com.dsltn.crud.service.GenreService;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    private AuthorService authorService;
    private GenreService genreService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcomePage(ModelMap model){
        model.addAttribute("bookList", this.bookService.getAllBooks());
        model.addAttribute("client",new Client());
        model.addAttribute("author", new Author());
        return "index";    
    }
    @RequestMapping(value = "/books/author", method = RequestMethod.POST)
    public String printBooksByAuthor(@ModelAttribute Author author, ModelMap model){
        List<Book> list;
        if(author.getAuthorName() == "" || author.getAuthorSurname() == ""){
            list = bookService.getAllBooks();
        } else
        list = bookService.getByAuthor(author.getAuthorName(), author.getAuthorSurname());
        model.put("bookList",list);
        model.put("book",new Book());
        model.put("client", new Client());
        model.put("author", new Author());
        return "index";
    }
    
    public void setBookService(BookService bookService){
        this.bookService = bookService;
    }
     public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }
    
   
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String bookList(Model model){
        model.addAttribute("bookList", this.bookService.getAllBooks());
        model.addAttribute("book", new Book());
        return "books";
    }
    @RequestMapping(value = "/books/add",method = RequestMethod.POST)
    public String add(@ModelAttribute("book") Book book){
        Author a = book.getAuthor();
        String name = a.getAuthorName();
        String surname = a.getAuthorSurname();
        Author author = authorService.getAuthorByNameAndSurname(name, surname);
        if(author != null){
            book.setAuthor(author);
        }
        else{
            authorService.add(a);
        }
        Genre genre = genreService.getGenreByTitle(book.getGenre().getGenreTitle());
        if(genre != null)
             book.setGenre(genre);
        else
            genreService.add(book.getGenre());
            bookService.add(book);
        return "redirect:/books";
    }
    @RequestMapping(value = "/books/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute("book") Book book,Model model){
        Author a = book.getAuthor();
        Book b = bookService.getBookByid(book.getId());
        if(b == null){
            model.addAttribute("errorMessage","Book with this id not exist");
            model.addAttribute("goBack","<a href=\"../books\">Go back and try again</a>");
            return "error";
        }//if books does not exist throw error
        String name = a.getAuthorName();
        String surname = a.getAuthorSurname();
        Author author = authorService.getAuthorByNameAndSurname(name, surname);
        if(author != null){
            book.setAuthor(author);
        }
        else{
            authorService.add(a);
        }
        Genre genre = genreService.getGenreByTitle(book.getGenre().getGenreTitle());
        if(genre != null)
             book.setGenre(genre);
        else
            genreService.add(book.getGenre());
        bookService.edit(book);
        return "redirect:/books";
    }
    

   
    
}
