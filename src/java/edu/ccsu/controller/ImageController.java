/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ccsu.controller;

import edu.ccsu.beans.Image;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;

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
    @ManagedProperty (value = "#{image}")
    private Image image;
    
    public String saveImage() {
        String returnVal = "error";
        try {
            userTransaction.begin();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.persist(image);
            userTransaction.commit();
            entityManager.close();
            returnVal = "confirmationImage";
        }catch(Exception e) {
            e.printStackTrace();
        }
        return returnVal;
    }
    
    public Image getImage() {
        return image;
    }
    
    public void setImage(Image newImage) {
        image = newImage;
    }
}
