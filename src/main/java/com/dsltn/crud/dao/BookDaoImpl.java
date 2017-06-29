/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.dao;

import com.dsltn.crud.model.Book;
import java.util.List;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author desolation
 */
@Repository("bookDao")
public class BookDaoImpl implements BookDao{
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    @Override
    public void add(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
    }

    @Override
    public void edit(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.update(book);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book b = (Book)session.load(Book.class, new Integer(id));
        if(b != null)
            session.delete(b);
    }

    @Override
    public List<Book> getByGenre(String bookGenre) {
        Session session = sessionFactory.getCurrentSession();
        List<Book> books = session.createQuery("from book where book_genre = " + bookGenre).list();
        return books;
    }

    @Override
    public List<Book> getByAuthor(String author) {
        Session session = sessionFactory.getCurrentSession();
        List<Book> books = session.createQuery("from book where book_author = " + author).list();
        return books;
    }

    @Override
    public List<Book> getAllBooks() {
        Session session = sessionFactory.getCurrentSession();
        List<Book> books = session.createQuery("from book").list();
        return books;
    }
    
}
