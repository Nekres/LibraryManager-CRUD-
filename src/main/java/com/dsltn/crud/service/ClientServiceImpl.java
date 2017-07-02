/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.service;

import com.dsltn.crud.dao.ClientDao;
import com.dsltn.crud.model.Client;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author dsltn
 */
@Service
public class ClientServiceImpl implements ClientService{
    
    ClientDao clientDao;

    @Override
    @Transactional
    public void add(Client client) {
        this.clientDao.add(client);
    }

    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }
    
    
}
