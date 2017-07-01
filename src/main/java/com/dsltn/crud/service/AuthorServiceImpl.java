/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.service;

import com.dsltn.crud.dao.AuthorDao;
import com.dsltn.crud.model.Author;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author dsltn
 */
@Service
@Transactional
public class AuthorServiceImpl implements AuthorService{
    private AuthorDao authorDao;

    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }
    
    @Override
    @Transactional
    public void add(Author author) {
        authorDao.add(author);
    }

    @Override
    @Transactional
    public Author getAuthorById(int id) {
        return authorDao.getAuthorById(id);
    }

    @Override
    @Transactional
    public Author getAuthorByNameAndSurname(String name, String surname) {
        return authorDao.getAuthorByNameAndSurname(name, surname);
    }
    
}
