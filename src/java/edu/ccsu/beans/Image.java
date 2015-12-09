/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ccsu.beans;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.servlet.http.Part;

/**
 *
 * @author Curtis
 */
@ManagedBean
@Entity
//@ManagedProperty (value = "#{image}")
//@Table (name = "image")
public class Image implements Serializable {
    @Id
    @GeneratedValue
    private String title;
    @Lob
    private byte[] content;
    
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    
    public Image() {
    }
    
    public Image(Part art, String title) throws IOException{
         InputStream input = art.getInputStream();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[10240];
            for (int length = 0; (length = input.read(buffer)) > 0;) output.write(buffer, 0, length);
            content = output.toByteArray();
            this.title = title;
    }
    
    public void setContent(byte[] content){
        this.content = content;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String newTitle) {
        title = newTitle;
    }
    
    public byte[] getContent() {
        return content;
    }
}