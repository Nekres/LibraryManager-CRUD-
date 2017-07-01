/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.service;

import com.dsltn.crud.dao.GenreDao;
import com.dsltn.crud.model.Genre;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author dsltn
 */
@Service
@Transactional
public class GenreServiceImpl implements GenreService{
    private GenreDao genreDao;

    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }
    
    @Override
    @Transactional
    public void add(Genre genre) {
        genreDao.add(genre);
    }

    @Override
    @Transactional
    public Genre getGenreById(int id) {
        return genreDao.getGenreById(id);
    }
    
}
