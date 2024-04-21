package com.example.uia.models;

public class Mentor {
    private String name;
    private String email;
    private String linkedIn;
    private String workExperience;
    private String workAs;

    public Mentor(String name, String email, String linkedIn, String workExperience, String workAs) {
        this.name = name;
        this.email = email;
        this.linkedIn = linkedIn;
        this.workExperience = workExperience;
        this.workAs = workAs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getWorkAs() {
        return workAs;
    }

    public void setWorkAs(String workAs) {
        this.workAs = workAs;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }
}
