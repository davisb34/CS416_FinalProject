/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ccsu.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Curtis
 */
@ManagedBean
@Entity
public class ImageUpload implements Serializable {
    
    @Id
    @GeneratedValue
    private String title;
    private byte[] art;
    
    public ImageUpload(){
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    public byte[] getArt() {
        return art;
    }
    public void setArt(byte[] art) {
        this.art = art;
    }
}
