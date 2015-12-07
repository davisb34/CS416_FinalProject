/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ccsu.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author Curtis
 */
@ManagedBean
@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue
    private String email;
    private String username;
    
    @ManyToMany
    @JoinTable(name="CustomerImage",
            joinColumns=@JoinColumn(name="customerId",referencedColumnName="email"),
            inverseJoinColumns=@JoinColumn(name="imageId",referencedColumnName="title"))
    private Set<Image> images = new HashSet();
    
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
    
    public Set<Image> getImages() {
        return images;
    }
    public void setImages(Set<Image> images) {
        this.images = images;
    }
    
}
