/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.service;

import com.dsltn.crud.model.Genre;

/**
 *
 * @author dsltn
 */
public interface GenreService {

    public void add(Genre genre);

    public Genre getGenreById(int id);
}
