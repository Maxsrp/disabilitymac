package com.marktrs.macapp.Model;

import java.util.Date;

/**
 * Created by Mark on 5/5/2017.
 */

public class User {
    private String firstName;
    private String lastName;
//    private Recruiter recruiterProfile;
//    private Worker workerProfile;
    private String uID;

    public User() {

    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
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
