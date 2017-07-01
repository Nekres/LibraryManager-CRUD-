/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.dao;

import com.dsltn.crud.model.Author;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dsltn
 */

public interface AuthorDao {
    public void add(Author author);
    public Author getAuthorById(int id);
    
}
