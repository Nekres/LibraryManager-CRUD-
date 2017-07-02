/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.dao;

import com.dsltn.crud.model.Book;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        book.setId(0);
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
        Book b = (Book)session.get(Book.class, new Integer(id));
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
    public List<Book> getByAuthor(String author, String authorSurname) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Book b where b.author.authorName = :author and b.author.authorSurname = "
                + ":authorsurname");    
        query.setParameter("author", author);
        query.setParameter("authorsurname", authorSurname);
        List<Book> books =  query.list();
        for(Book b: books)
            System.out.println(b.getAuthor().getAuthorName());
        return books;
    }
    @Override
    public List<Book> getAllBooks() {
        Session session = sessionFactory.getCurrentSession();
        List<Book> books = session.createQuery("from Book").list();
        return books;
    }

    @Override
    public Book getBookById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = (Book)session.get(Book.class, new Integer(id));
        return book;
    }
    
    
}
