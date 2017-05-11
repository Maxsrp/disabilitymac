package com.marktrs.macapp.Model;

import java.io.Serializable;

/**
 * Created by Mark on 5/5/2017.
 */

public class Job implements Serializable {

    private String jobName;
    private String symptomType;
    private String requiredEducation;
    private String requiredSkill;
    private String workplace;
    private String payment;
    private String ownerUID;

    public Job() {

    }

    public Job(String jobName, String symptomType, String requiredEducation, String requiredSkill, String workplace, String payment, String ownerUID) {
        this.jobName = jobName;
        this.symptomType = symptomType;
        this.requiredEducation = requiredEducation;
        this.requiredSkill = requiredSkill;
        this.workplace = workplace;
        this.payment = payment;
        this.ownerUID = ownerUID;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getSymptomType() {
        return symptomType;
    }

    public void setSymptomType(String symptomType) {
        this.symptomType = symptomType;
    }

    public String getRequiredEducation() {
        return requiredEducation;
    }

    public void setRequiredEducation(String requiredEducation) {
        this.requiredEducation = requiredEducation;
    }

    public String getRequiredSkill() {
        return requiredSkill;
    }

    public void setRequiredSkill(String requiredSkill) {
        this.requiredSkill = requiredSkill;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getOwnerUID() {
        return ownerUID;
    }

    public void setOwnerUID(String ownerUID) {
        this.ownerUID = ownerUID;
    }
}
