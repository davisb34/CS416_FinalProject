package edu.ccsu.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Curtis
 */
@ManagedBean
@Entity
public class Customer implements Serializable {
    
    private String firstName;
    private String lastName;
    @Id
    private String email;
    private String usState;

//    @ManyToMany
//    @JoinTable(name="CustomerImage",
//            joinColumns= @JoinColumn(name="customerId",referencedColumnName="id"),
//            inverseJoinColumns= @JoinColumn(name="imageId",referencedColumnName="title"))
    
    @OneToMany(mappedBy="customer", orphanRemoval=true)
    private Set<Image> images;

    public Customer() {
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public String getUsState() {
        return usState;
    }
    public void setUsState(String usState) {
        this.usState = usState;
    }

//    public Long getId() {
//        return id;
//    }
//    public void setId(Long id) {
//        this.id = id;
//    }

    public Set<Image> getImages() {
        return images;
    }
    public void setImages(Set<Image> images) {
        this.images = images;
    }

}