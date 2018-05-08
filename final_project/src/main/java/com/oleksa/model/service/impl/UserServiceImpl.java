package com.oleksa.model.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

import com.oleksa.model.dao.UserDao;
import com.oleksa.model.entity.User;
import com.oleksa.model.entity.UserRole;
import com.oleksa.model.exception.InvalidCredentialsException;
import com.oleksa.model.exception.NotUniqueEmailException;
import com.oleksa.model.exception.NotUniqueNameException;
import com.oleksa.model.service.UserService;

public class UserServiceImpl implements UserService {
    
    private UserDao userDao;
    
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findUserByCredentials(String name, String password) throws InvalidCredentialsException {
        Optional<User> optional = userDao.findByName(name);
        if (!optional.isPresent() || !isValidPassword(optional.get(), password)) {
            throw new InvalidCredentialsException();
        }
        return optional.get();
    }

    private boolean isValidPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    @Override
    public User create(User user) throws NotUniqueNameException, NotUniqueEmailException {
        return userDao.create(user);
    }


}
