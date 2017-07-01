/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.dao;

import com.dsltn.crud.model.Genre;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dsltn
 */
@Repository("genreDao")
public class GenreDaoImpl implements GenreDao{
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
        

    @Override
    public void add(Genre genre) {
        Session session = sessionFactory.getCurrentSession();
        genre.setGenreId(0);
        session.persist(genre);
    }

    @Override
    public Genre getGenreById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Genre genre = session.load(Genre.class, new Integer(id));
        return genre;
    }

    @Override
    public Genre getGenreByTitle(String title) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Genre where genreTitle = :title");
        query.setParameter("title", title.toLowerCase());
        List<Genre> list = query.list();
        Genre g = null;
        if(list.size() > 0)
            g = list.get(0);
        return g;
    }
    
}
