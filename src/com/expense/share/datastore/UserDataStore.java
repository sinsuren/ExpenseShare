package com.expense.share.datastore;

import com.expense.share.datastore.api.UserDataStoreDao;
import com.expense.share.entity.User;

import java.util.HashMap;

/**
 * Created by surender.s on 07/09/19.
 */
public class UserDataStore implements UserDataStoreDao {

    //emailId to UserMap
    private static final HashMap<String, User> userMap = new HashMap<>();

    @Override
    public void createUser(User user) {

        if (!userMap.containsKey(user.getEmailId())) {
            userMap.put(user.getEmailId(), user);
        }
    }

    @Override
    public User getUser(String emailId) throws Exception {
        if (userMap.containsKey(emailId)) {
            return userMap.get(emailId);
        }

        throw new Exception("User Not Registered");
    }


}
