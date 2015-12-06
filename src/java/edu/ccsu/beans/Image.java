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
import javax.persistence.Lob;

/**
 *
 * @author Curtis
 */
@Entity
public class Image implements Serializable {
    @Id
    @GeneratedValue
    private String title;
    
    @Lob
    private byte[] content;
    
    public Image() {
    }
    
    public void setContent(byte[] content){
        this.content = content;
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