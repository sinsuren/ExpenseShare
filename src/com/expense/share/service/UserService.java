package com.expense.share.service;

import com.expense.share.entity.User;

/**
 * Created by surender.s on 07/09/19.
 */
public interface UserService {

    void createUser(String name, String emailId);

    User getUser(String emailId) throws Exception;
}
