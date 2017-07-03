/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author dsltn
 */
@Entity
@Table(name = "client")
public class Client {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Size(min= 3, message = "First name can't be empty.")
    @Column(name = "first_name")
    private String firstName;
    @Size(min=4, max=16, message ="Last name can't be empty.")
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "address")
    @Size(min=4, message ="Address can't be empty.")
    private String address;
    @Column(name = "quantity")
    private int quantity;
    @Transient // we don't need to store this at db
    public List<Integer> bookCounter;

    public List<Integer> getBookCounter() {
        return bookCounter;
    }

    public void setBookCounter(List<Integer> bookCounter) {
        this.bookCounter = bookCounter;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
