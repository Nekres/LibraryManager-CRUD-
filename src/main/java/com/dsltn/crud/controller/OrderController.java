/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.controller;

import com.dsltn.crud.model.Author;
import com.dsltn.crud.model.Client;
import com.dsltn.crud.service.ClientService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author dsltn
 */
@Controller
@RequestMapping("/order")
public class OrderController extends WebMvcConfigurerAdapter{
    private ClientService clientService;
    
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String orderPage(@ModelAttribute(name = "client") @Valid Client client, ModelMap model, BindingResult bindingResult){
        model.addAttribute("client", new Client());
        model.addAttribute("author", new Author());
        if(client.getFirstName() == "" && client.getLastName() == "" && client.getAddress() == ""){
            model.addAttribute("errorMessage","Some fields is empty. You must to fill them all.");
            model.addAttribute("goBack","<a href=\"../\">Go back and try again</a>");
            return "/error";
        }
        client.setQuantity(client.bookCounter.size());
        client.setUserId(0);
        clientService.add(client);
        return "redirect:/"; 
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
}
