package com.marktrs.macapp.Model;

/**
 * Created by Mark on 5/5/2017.
 */

public class Worker {

    private String symptom;
    private String educaton;
    private String skill;

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
}
