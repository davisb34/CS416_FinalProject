/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ccsu.controller;

import edu.ccsu.beans.Customer;
import edu.ccsu.beans.Image;
import java.io.ByteArrayOutputStream;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import javax.servlet.http.Part;
import org.omnifaces.util.Utils;

/**
 *
 * @author Curtis
 */
@ManagedBean
public class ImageController {
    
    @PersistenceUnit(unitName = "WebApplication1PU")
    private EntityManagerFactory entityManagerFactory;
    @Resource
    private UserTransaction userTransaction;
    private Part art;
    private String title;
    private Set<Customer> customers;
    
    
    public String saveImage() throws IOException {
        
        String returnVal = "error";
        try {
            userTransaction.begin();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            Image image = new Image();
            InputStream input = art.getInputStream();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[10240];
            for (int length = 0; (length = input.read(buffer)) > 0;) output.write(buffer, 0, length);
            image.setContent(output.toByteArray());
            image.setTitle(title);
            entityManager.persist(image);
            userTransaction.commit();
            entityManager.close();
            
            returnVal = "confirmationImage";
        }catch(Exception e) {
            e.printStackTrace();
        }
        return returnVal;
    }
    
    public String deleteImage() {
        String returnValue = "error";
        try {
            userTransaction.begin();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            Image imageToDelete = entityManager.find(Image.class, title);
            entityManager.remove(imageToDelete);
            userTransaction.commit();
            returnValue = "deleteSuccessful";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
    }
    
    public List getAllImages() {
        List<Image> images = new ArrayList();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String selectSQL = "SELECT i FROM Image i";
        try {
            Query selectQuery = entityManager.createQuery(selectSQL);
            images = selectQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return images;
    }
    
    public List getAllImageTitles() {
        List<Image> images = new ArrayList();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String selectSQL = "SELECT i.title FROM Image i";
        try {
            Query selectQuery = entityManager.createQuery(selectSQL);
            images = selectQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return images;
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
    
    public void setArt(Part art) {
       this.art = art;
    }
    
    public Set<Customer> getCustomers() {
        return customers;
    }
    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
    
//    public Image getImage() {
//        return image;
//    }
//    
//    public void setImage(Image newImage) {
//        image = newImage;
//    }
}
