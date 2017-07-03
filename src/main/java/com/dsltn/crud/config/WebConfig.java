/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.config;

import com.dsltn.crud.controller.*;
import com.dsltn.crud.dao.*;
import com.dsltn.crud.model.Book;
import com.dsltn.crud.service.*;
import java.io.IOException;
import java.util.Properties;
import javax.inject.Named;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.*;

/**
 *
 * @author desolation
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.dsltn.crud"})
@EnableTransactionManagement
public class WebConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/pages/**").addResourceLocations("/pages/**");
        
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
    @Bean("orderController")
    public OrderController getOrderController(@Named ("clientService") ClientService clientService, @Named("bookService") 
    BookService bookService){
        OrderController oc = new OrderController();
        oc.setClientService(clientService);
        oc.setBookService(bookService);
        return oc;
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
    @Bean("clientService")
    public ClientService getClientService(@Named("clientDao") ClientDao clientDao){
        ClientServiceImpl csi = new ClientServiceImpl();
        csi.setClientDao(clientDao);
        return csi;
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
    @Bean("clientDao")
    public ClientDao getClientDao(@Named("sessionFactory") SessionFactory sessionFactory){
        ClientDaoImpl cdi = new ClientDaoImpl();
        cdi.setSessionFactory(sessionFactory);
        return cdi;
    }
    @Bean("sessionFactory")
    public SessionFactory getSessionFactory(@Named("dataSource") DataSource dataSource){
        org.springframework.orm.hibernate5.LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setAnnotatedClasses(BookDao.class, ClientDao.class,GenreDao.class,AuthorDao.class);
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
        bsd.setUsername("librarian");
        bsd.setPassword("librarian");
        return bsd;
    }
    Properties hibernateProperties(){
        org.hibernate.cfg.Configuration config = new org.hibernate.cfg.Configuration();
        config.addAnnotatedClass(Book.class);
        config = config.configure();
        Properties p = config.getProperties();
        return p;
    }
    @Bean
    public HibernateTransactionManager transactionManager(@Named("sessionFactory") SessionFactory sessionFactory){
            HibernateTransactionManager htm = new HibernateTransactionManager();
                    htm.setSessionFactory(sessionFactory);
            return htm;
        }
        
    }
    
    
    
    

