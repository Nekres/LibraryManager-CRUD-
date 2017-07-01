/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.service;

import com.dsltn.crud.model.Author;

/**
 *
 * @author dsltn
 */
public interface AuthorService {
    
    public void add(Author author);
    public Author getAuthorById(int id);
}
