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
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
        model.addAttribute("genre", new Genre());
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
        model.addAttribute("genre", new Genre());
        return "index";
    }
    @RequestMapping(value = "books/genre", method = RequestMethod.POST)
    public String printBooksByGenre(@ModelAttribute Genre genre, ModelMap model){
        List<Book> list;
        if(genre.getGenreTitle() == ""){
            list = bookService.getAllBooks();
        }else
            list = bookService.getByGenre(genre.getGenreTitle());
        model.put("bookList", list);
        model.put("book", new Book());
        model.put("client", new Client());
        model.put("author", new Author());
        model.put("genre", new Genre());
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
        model.addAttribute("bookAdd", new Book());
        model.addAttribute("bookEdit", new Book());
        return "books";
    }
    @RequestMapping(value = "/books/add",method = RequestMethod.POST)
    public ModelAndView add(@Valid @ModelAttribute("bookAdd") Book book, BindingResult bindingResult, Model model){
        model.addAttribute("bookList", this.bookService.getAllBooks());
        model.addAttribute("bookAdd", new Book());
        model.addAttribute("bookEdit", new Book());
        if(bindingResult.hasErrors()){
            return new ModelAndView("books",bindingResult.getModel());
        }
        Author a = book.getAuthor();
        String name = a.getAuthorName();
        String surname = a.getAuthorSurname();
        Author author = authorService.getAuthorByNameAndSurname(name, surname);
        if(author != null){ //to not create new one similar author
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
        return new ModelAndView("redirect:/books",bindingResult.getModel());
    }
    @RequestMapping(value = "/books/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("bookEdit") Book book, BindingResult bindingResult, Model model){
        Author a = book.getAuthor();
        model.addAttribute("bookAdd", new Book());
        model.addAttribute("bookEdit", new Book());
        Book b = bookService.getBookByid(book.getId());
        if(b == null){
            model.addAttribute("infoMessage","Book with this id not exist");
            model.addAttribute("goBack","<a href=\"../books\">Back to the main page.</a>");
            return new ModelAndView("info");
        }//if books does not exist throw error
        if(bindingResult.hasErrors())
            return new ModelAndView("books", bindingResult.getModel());
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
        return new ModelAndView("redirect:/books",bindingResult.getModel());
    }
    @RequestMapping(value = "/details/{id}")
    public String details(@PathVariable(value = "id") int id, Model model){
        Book book = bookService.getBookByid(id);
        model.addAttribute("book", book);
        return "details";
    }
   
    
}
