package com.expense.share.entity;

/**
 * Created by surender.s on 07/09/19.
 */
public class User {

    private String userName;
    //Used as primary construct
    private String emailId;


    public User(String userName, String emailId) {
        this.userName = userName;
        this.emailId = emailId;
    }

    public String getUserName() {
        return userName;
    }


    public String getEmailId() {
        return emailId;
    }

}
