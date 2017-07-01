/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.dao;

import com.dsltn.crud.model.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dsltn
 */
@Repository("authorDao")
public class AuthorDaoImpl implements AuthorDao{
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void add(Author author) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(author);
    }

    @Override
    public Author getAuthorById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Author author = session.load(Author.class, new Integer(id));
        return author;
    }
    
}
