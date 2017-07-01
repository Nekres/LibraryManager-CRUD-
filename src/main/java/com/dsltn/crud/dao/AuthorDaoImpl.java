/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.dao;

import com.dsltn.crud.model.Author;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
        author.setAuthorId(0);
        session.persist(author);
    }

    @Override
    public Author getAuthorById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Author author = session.load(Author.class, new Integer(id));
        return author;
    }

    @Override
    public Author getAuthorByNameAndSurname(String name, String surname) {
        Session session = sessionFactory.getCurrentSession();
        String hquery = "FROM Author a WHERE a.authorName = :name AND a.authorSurname = :surname";
        Query query = session.createQuery(hquery);
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        List<Author> list = query.list();
        Author a = null;
        if(list.size() > 0)
            a = list.get(0);
        return a;
    }
    
}
