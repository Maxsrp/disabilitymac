package com.marktrs.macapp.Model;

/**
 * Created by Mark on 5/5/2017.
 */

public class Recruiter {
    private String companyName;
    private String companyLocation;
    private String companyDescription;

    public Recruiter() {
    }

    public Recruiter(String companyName, String companyLocation, String companyDescription) {
        this.companyName = companyName;
        this.companyLocation = companyLocation;
        this.companyDescription = companyDescription;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }
}
