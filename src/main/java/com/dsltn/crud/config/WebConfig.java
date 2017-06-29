/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.config;

import com.dsltn.crud.controller.BookController;
import com.dsltn.crud.dao.BookDao;
import com.dsltn.crud.dao.BookDaoImpl;
import com.dsltn.crud.model.Book;
import com.dsltn.crud.service.BookService;
import com.dsltn.crud.service.BookServiceImpl;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author desolation
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
    

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/pages/**").addResourceLocations("/pages/");
        
    }
    
    @Bean
    public InternalResourceViewResolver setup(){
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/pages/");
        internalResourceViewResolver.setSuffix(".jsp");
        internalResourceViewResolver.setViewClass(JstlView.class);
        return internalResourceViewResolver;
    }
    @Bean("bookController")
    @Inject
    public BookController getBookController(@Named ("bookService") BookService bookService){
        BookController bc = new BookController();
        bc.setBookService(bookService);
        return bc;
        
    } 
    @Bean("bookService")
    @Inject
    public BookService getBookService(@Named("bookDao") BookDao bookDao){
        BookServiceImpl bs = new BookServiceImpl();
        bs.setBookDao(bookDao);
        return bs;
    } 
    
    @Bean("bookDao")
    @Inject
    public BookDao getBookDao(@Named("sessionFactory") SessionFactory sessionFactory){
        BookDaoImpl bdi = new BookDaoImpl();
        bdi.setSessionFactory(sessionFactory);
        return bdi;
    }
    
    @Bean("sessionFactory")
    @Inject
    public SessionFactory getSessionFactory(@Named("dataSource") DataSource dataSource){
        org.springframework.orm.hibernate5.LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setPackagesToScan(new String[]{"com.dsltn.crud.dao","com.dsltn.crud.model"});
        return lsfb.getObject();
    }
    
    @Bean("dataSource")
    @Inject
    public BasicDataSource getDataSource(){
        BasicDataSource bsd = new BasicDataSource();
        bsd.setDriverClassName("com.mysql.jdbc.Driver");
        bsd.setUrl("jdbc:mysql://localhost:3306/library");
        bsd.setUsername("root");
        bsd.setPassword("finished");
        return bsd;
    }
        
    }
    
    
    
    

