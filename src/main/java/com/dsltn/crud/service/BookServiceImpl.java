/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.service;

import com.dsltn.crud.dao.BookDao;
import com.dsltn.crud.model.Book;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author desolation
 */
@Service
@Transactional
public class BookServiceImpl implements BookService{
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    
    @Override
    @Transactional
    public void add(Book book) {
        this.bookDao.add(book);
    }

    @Override
    @Transactional
    public void edit(Book book) {
        this.bookDao.edit(book);
    }

    @Override
    @Transactional
    public void delete(int id) {
        this.bookDao.delete(id);
    }

    @Override
    @Transactional
    public List<Book> getByGenre(String bookGenre) {
        return this.bookDao.getByGenre(bookGenre);
    }

    @Override
    @Transactional
    public List<Book> getByAuthor(String author, String authorSurname) {
        return this.bookDao.getByAuthor(author,authorSurname);
    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return this.bookDao.getAllBooks();
    }
    
    @Override
    @Transactional
    public Book getBookByid(int id) {
        return this.bookDao.getBookById(id);
    }
    
    
}
