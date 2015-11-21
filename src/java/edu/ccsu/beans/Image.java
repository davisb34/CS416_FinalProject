/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ccsu.beans;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import org.jcp.xml.dsig.internal.dom.Utils;
/**
 *
 * @author Curtis
 */
@ManagedBean
@SessionScoped
public class Image implements Serializable {

    private String title;
    private Part art;
    private byte[] content;
    
    public Image() {
    }
    
    public String read() throws IOException {
        content = Utils.readBytesFromStream(art.getInputStream());
        return "inputImage";
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
