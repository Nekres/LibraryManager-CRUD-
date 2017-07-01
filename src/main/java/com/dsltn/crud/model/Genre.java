/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author dsltn
 */
@Entity("Genre")
@Table("genre")
public class Genre {
    @Id
    @Column("genre_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genreId;
    @Column("genre_title")
    private String genre_title;

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getGenre_title() {
        return genre_title;
    }

    public void setGenre_title(String genre_title) {
        this.genre_title = genre_title;
    }
    
    
}
