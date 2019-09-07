package com.expense.share.service.impl;

import com.expense.share.datastore.UserDataStore;
import com.expense.share.datastore.api.UserDataStoreDao;
import com.expense.share.entity.User;
import com.expense.share.service.UserService;

/**
 * Created by surender.s on 07/09/19.
 */
public class UserServiceImpl implements UserService {

    private UserDataStoreDao userDataStoreDao = new UserDataStore();

    @Override
    public void createUser(String name, String emailId) {
        User user = new User(name, emailId);

        userDataStoreDao.createUser(user);
    }

    @Override
    public User getUser(String emailId) throws Exception {

        try {
            return userDataStoreDao.getUser(emailId);
        } catch (Exception e) {
            throw e;
        }

    }
}
