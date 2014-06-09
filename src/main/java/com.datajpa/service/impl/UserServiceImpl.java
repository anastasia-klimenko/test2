package com.datajpa.service.impl;

import com.datajpa.dao.UserRepository;
import com.datajpa.dao.model.User;
import com.datajpa.exception.EntityNotFound;
import com.datajpa.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final static Logger LOG = Logger.getLogger(UserServiceImpl.class);

    @Resource
    private UserRepository userRepository;


    protected void setUserRepository(UserRepository UserRepository) {
        this.userRepository = UserRepository;
    }

    @Transactional
    @Override
    public User create(User user) {
        LOG.info("Creating a new User with name: " + user.getFirstName()+" "+user.getLastName());
        return userRepository.save(user);
    }

    @Transactional(rollbackFor = EntityNotFound.class)
    @Override
    public User delete(Long userId) throws EntityNotFound {
        LOG.info("Deleting User with id: " + userId);

        User deleted = userRepository.findOne(userId);

        if (deleted == null) {
            LOG.info("No User found with id: " + userId);
            throw new EntityNotFound();
        }

        userRepository.delete(deleted);
        return deleted;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        System.out.println("findAll");
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(Long id) {
        LOG.info("Finding User by id: " + id);
        return userRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User findByFirstName(String firstName) {
        LOG.info("Finding User by firstName: " + firstName);
        return userRepository.findByFirstName(firstName);
    }
    @Transactional(readOnly = true)
    @Override
    public List<User> findByName(String firstName,String lastName) {
        LOG.info("Finding User by name: " + firstName+" "+lastName);
        return userRepository.findByName(firstName,lastName);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findWithNative() {
        LOG.info("native query");
        return userRepository.findWithNative();
    }
}