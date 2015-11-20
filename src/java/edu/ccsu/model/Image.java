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
public class Image implements Serializable {
    @Id
    @GeneratedValue
    private String title;
    private byte[] art;
    
    public Image() {
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String newTitle) {
        title = newTitle;
    }
    public byte[] getArt() {
        return art;
    }
    public void setArt(byte[] newArt) {
        art = newArt;
    }
}
