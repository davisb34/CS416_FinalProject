package edu.ccsu.controller;

import edu.ccsu.beans.EmailList;
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
public class EmailController {
    @PersistenceUnit(unitName = "WebApplication1PU")
    private EntityManagerFactory entityManagerFactory;
    @Resource
    private UserTransaction userTransaction;
    @ManagedProperty(value = "#{email}")
    private EmailList email;
    
    public String saveEmail() {
        String returnVal = "error";
        try {
            userTransaction.begin();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.persist(email);
            userTransaction.commit();
            entityManager.close();
            returnVal = "confirmation";
        }catch(Exception e) {
            e.printStackTrace();
        }
        return returnVal;
    }
    
    
}
