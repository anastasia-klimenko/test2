package com.datajpa.dao.model;

import javax.persistence.*;

@Entity
@Table(name="USER")
@NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE LOWER(u.firstName) = LOWER(?1) AND LOWER(u.lastName) = LOWER(?2)")
@NamedNativeQuery (name="User.findWithNative", query = "select * from USER u", resultClass = User.class)

public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}