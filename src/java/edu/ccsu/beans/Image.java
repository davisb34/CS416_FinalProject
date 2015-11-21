/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ccsu.beans;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.servlet.http.Part;
import org.jcp.xml.dsig.internal.dom.Utils;
/**
 *
 * @author Curtis
 */
@ManagedBean
@Entity
public class Image implements Serializable {
    @Id
    @GeneratedValue
    private String title;
    private Part art;
    private byte[] content;
    
    public Image() {
    }
    
    public void read() throws IOException {
        content = Utils.readBytesFromStream(art.getInputStream());
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String newTitle) {
        title = newTitle;
    }
    
    public Part getArt() {
        return art;
    }
    public void setArt(Part newArt) {
        art = newArt;
    }
    
    public byte[] getContent() {
        return content;
    }
}