package com.datajpa.dao;

import com.datajpa.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


   public User findByFirstName(String firstName);
    public List<User> findByName(String firstName,String lastName);
    public List<User> findWithNative();
}
