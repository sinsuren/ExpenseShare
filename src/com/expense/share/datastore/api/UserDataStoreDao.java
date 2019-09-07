package com.expense.share.datastore.api;

import com.expense.share.entity.User;

/**
 * Created by surender.s on 07/09/19.
 */
public interface UserDataStoreDao {

    void createUser(User user);

    User getUser(String emailId) throws Exception;
}
