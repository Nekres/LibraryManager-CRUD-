/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.config;

import com.dsltn.crud.controller.BookController;
import com.dsltn.crud.dao.AuthorDao;
import com.dsltn.crud.dao.AuthorDaoImpl;
import com.dsltn.crud.dao.BookDao;
import com.dsltn.crud.dao.BookDaoImpl;
import com.dsltn.crud.dao.GenreDao;
import com.dsltn.crud.dao.GenreDaoImpl;
import com.dsltn.crud.model.Book;
import com.dsltn.crud.service.AuthorService;
import com.dsltn.crud.service.AuthorServiceImpl;
import com.dsltn.crud.service.BookService;
import com.dsltn.crud.service.BookServiceImpl;
import com.dsltn.crud.service.GenreService;
import com.dsltn.crud.service.GenreServiceImpl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Scope;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author desolation
 */
@Configuration
//@EnableWebMvc
@ComponentScan(basePackages = {"com.dsltn.crud"})
@EnableTransactionManagement
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
    public BookController getBookController(@Named ("bookService") BookService bookService, @Named("authorService") AuthorService authorService, 
            @Named("genreService")GenreService genreService){
        BookController bc = new BookController();
        bc.setBookService(bookService);
        bc.setAuthorService(authorService);
        bc.setGenreService(genreService);
        return bc;
        
    } 
    @Bean("bookService")
    public BookService getBookService(@Named("bookDao") BookDao bookDao){
        BookServiceImpl bs = new BookServiceImpl();
        bs.setBookDao(bookDao);
        return bs;
    } 
    @Bean("authorService")
    public AuthorService getAuthorService(@Named("authorDao") AuthorDao authorDao){
        AuthorServiceImpl asi = new AuthorServiceImpl();
        asi.setAuthorDao(authorDao);
        return asi;
    }
    @Bean("genreService")
    public GenreService getGenreService(@Named("genreDao") GenreDao genreDao){
        GenreServiceImpl gsi = new GenreServiceImpl();
        gsi.setGenreDao(genreDao);
        return gsi;
    }
    @Bean("bookDao")
    public BookDao getBookDao(@Named("sessionFactory") SessionFactory sessionFactory){
        BookDaoImpl bdi = new BookDaoImpl();
        bdi.setSessionFactory(sessionFactory);
        return bdi;
    }
    @Bean("authorDao")
    public AuthorDao getAuthorDao(@Named("sessionFactory") SessionFactory sessionFactory){
        AuthorDaoImpl ad = new AuthorDaoImpl();
        ad.setSessionFactory(sessionFactory);
        return ad;
    }
    @Bean("genreDao")
    public GenreDao getGenreDao(@Named("sessionFactory") SessionFactory sessionFactory){
        GenreDaoImpl daoImpl = new GenreDaoImpl();
        daoImpl.setSessionFactory(sessionFactory);
        return daoImpl;
    }
    @Bean("sessionFactory")
    public SessionFactory getSessionFactory(@Named("dataSource") DataSource dataSource){
        org.springframework.orm.hibernate5.LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setAnnotatedClasses(BookDao.class);
        lsfb.setDataSource(dataSource);
        lsfb.setHibernateProperties(hibernateProperties());
        lsfb.setPackagesToScan(new String[]{"com.dsltn.crud.model"});
        try {
            lsfb.afterPropertiesSet();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lsfb.getObject();
    }
    
    @Bean("dataSource")
    public DriverManagerDataSource getDataSource(){
        DriverManagerDataSource bsd = new DriverManagerDataSource();
        bsd.setDriverClassName("com.mysql.jdbc.Driver");
        bsd.setUrl("jdbc:mysql://localhost:3306/library");
        bsd.setUsername("root");
        bsd.setPassword("finished");
        return bsd;
    }
    Properties hibernateProperties(){
        org.hibernate.cfg.Configuration config = new org.hibernate.cfg.Configuration();
        config.addAnnotatedClass(Book.class);
        config = config.configure();
        Properties p = config.getProperties();
       //p.put("hibernate_dialect", "org.hibernate.dialect.MySQLDialect");
       // p.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
       // p.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/library");
       // p.put("hibernate.connection.username", "root");
       // p.put("hibernate.connection.password", "finished");
        return p;
    }
    @Bean
    public HibernateTransactionManager transactionManager(@Named("sessionFactory") SessionFactory sessionFactory){
            HibernateTransactionManager htm = new HibernateTransactionManager();
                    htm.setSessionFactory(sessionFactory);
                   // htm.setDataSource(dataSource);
            return htm;
        }
        
    }
    
    
    
    

