/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.dao;

import com.dsltn.crud.model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dsltn
 */
@Repository("clientDao")
public class ClientDaoImpl implements ClientDao{
    
    private SessionFactory sessionFactory;
    
    @Override
    public void add(Client client) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(client);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
}
