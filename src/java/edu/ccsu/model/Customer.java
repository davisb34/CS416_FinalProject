/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ccsu.model;

import java.beans.*;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Curtis
 */
@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue
    private String email;
    private String username;
    
    public Customer() {
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String newEmail) {
        email = newEmail;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String newUsername) {
        username = newUsername;
    }
    
}
