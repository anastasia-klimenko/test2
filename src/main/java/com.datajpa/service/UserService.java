package com.datajpa.service;

import com.datajpa.dao.model.User;
import com.datajpa.exception.EntityNotFound;

import java.util.List;

public interface UserService {

   public User create(User user);
   public User delete(Long userId) throws EntityNotFound;
   public List<User> findAll();
   public User findById(Long userId);
   //public User update(User user) throws EntityNotFound;
   public User findByFirstName(String firstName);
    public List<User> findByName(String firstName,String lastName);
    public List<User> findWithNative();
}
