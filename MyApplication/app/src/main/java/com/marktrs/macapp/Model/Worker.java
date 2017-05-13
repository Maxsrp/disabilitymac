package com.marktrs.macapp.Model;

/**
 * Created by Mark on 5/5/2017.
 */

public class Worker {

    private String symptom;
    private String educaton;
    private String skill;
    private String location;
    private String lineId;
    private String facebookId;

    public Worker() {

    }

    public Worker(String symptom, String educaton, String skill) {
        this.symptom = symptom;
        this.educaton = educaton;
        this.skill = skill;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getEducaton() {
        return educaton;
    }

    public void setEducaton(String educaton) {
        this.educaton = educaton;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }
}
