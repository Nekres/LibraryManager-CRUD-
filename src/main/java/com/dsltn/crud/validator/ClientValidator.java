/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsltn.crud.validator;

import com.dsltn.crud.model.Client;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author dsltn
 */
public class ClientValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return Client.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "firstName", "Size.client.firstName");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "Size.client.lastName");
        ValidationUtils.rejectIfEmpty(errors, "address", "Size.client.address");
    }
    
}
