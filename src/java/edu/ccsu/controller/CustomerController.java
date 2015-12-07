/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ccsu.controller;

import edu.ccsu.beans.Customer;
import edu.ccsu.beans.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.http.Part;
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
    private String email;
    private Part art;
    private String title;
    
    public String saveCustomer() {
        String returnVal = "error";
        try {
            userTransaction.begin();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.persist(customer);
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
    
//    public List<Customer> getMatchingCustomer() {
//        List<Customer> customers = new ArrayList();
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        String selectSQL = "select c from Customer c where c.firstName like :name";
//        try {
//            Query selectQuery = entityManager.createQuery(selectSQL);
//            selectQuery.setParameter("name", customer.getFirstName() + "%");
//            customers = selectQuery.getResultList();
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        return customers;
//    }
    
    public String getEmail() {
        
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Customer addImageToCustomer (){
        try {
            userTransaction.begin();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            customer = entityManager.find(Customer.class, email);
            Image image = new Image(art, title);
            image.setCustomer(customer);
            Set<Image> images = customer.getImages();
            images.add(image);
            customer.setImages(images);
            entityManager.persist(image);
            entityManager.persist(customer);
            userTransaction.commit();
            entityManager.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return customer;     
    }
    
    public Customer getMatchingCustomer() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            customer = entityManager.find(Customer.class, email);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    public void setArt(Part art) {
        this.art = art;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Part getArt() {
        return art;
    }

    public String getTitle() {
        return title;
    }
    
    
}
