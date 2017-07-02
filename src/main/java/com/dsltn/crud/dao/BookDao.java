/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.dao;

import com.dsltn.crud.model.Book;
import java.util.List;

/**
 *
 * @author desolation
 */
public interface BookDao {
    public void add(Book book);
    public void edit(Book book);
    public void delete(int id);
    public List<Book> getByGenre(String bookGenre);
    public List<Book> getByAuthor(String author, String authorSurname);
    public List<Book> getAllBooks();
    public Book getBookById(int id);
    
}
