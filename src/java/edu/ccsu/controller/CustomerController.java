/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ccsu.controller;

import edu.ccsu.beans.Customer;
import edu.ccsu.beans.Image;
import java.util.Set;
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
public class CustomerController {
    
    @PersistenceUnit(unitName = "WebApplication1PU")
    private EntityManagerFactory entityManagerFactory;
    @Resource
    private UserTransaction userTransaction;
    @ManagedProperty(value = "#{customer}")
    private Customer customer;
    @ManagedProperty(value = "#{image}")
    private Image image;
    
    public String saveCustomer() {
        String returnVal = "error";
        try {
            userTransaction.begin();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            Set<Image> images = customer.getImages();
            images.add(image);
            customer.setImages(images);
            entityManager.persist(customer);
            Set<Customer> customers = image.getCustomers();
            customers.add(customer);
            image.setCustomers(customers);
            entityManager.persist(image);
            userTransaction.commit();
            entityManager.close();
            returnVal = "confirmation";
        }catch(Exception e) {
            e.printStackTrace();
        }
        return returnVal;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer newCustomer) {
        customer = newCustomer;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
}
