/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.controller;

import com.dsltn.crud.model.Author;
import com.dsltn.crud.model.Client;
import com.dsltn.crud.model.Genre;
import com.dsltn.crud.service.BookService;
import com.dsltn.crud.service.ClientService;
import javax.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author dsltn
 */
@Controller
@RequestMapping("/order")
public class OrderController extends WebMvcConfigurerAdapter{
    private ClientService clientService;
    private BookService bookService;

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public ModelAndView orderPage( @Valid @ModelAttribute(name = "client") Client client,BindingResult bindingResult, ModelMap model){
        model.addAttribute("bookList", this.bookService.getAllBooks());
        model.addAttribute("client",new Client());
        model.addAttribute("author", new Author());
        model.addAttribute("genre", new Genre());
        client.setQuantity(client.getBookCounter().size());
        if(bindingResult.hasErrors()){
            return new ModelAndView("index",bindingResult.getModel());
        }
        else
            clientService.add(client);
        model.addAttribute("infoMessage","Success. Thank you for your order.");
        model.addAttribute("goBack","<a href=\"../\">Go back and try again</a>");
        return new ModelAndView("/info",bindingResult.getModel());
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    
    
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
}
