package com.example.uia.models;

import com.example.uia.constant.Constants;
import com.example.uia.constant.Constants.EduStatus;
import com.example.uia.constant.Constants.Gender;
import com.example.uia.constant.Constants.UrbanStatus;

import java.io.Serializable;

public class UserModel implements Serializable {
    String name;
    String no;
    String email;
    EduStatus education;
    UrbanStatus urban;
    Gender gender;
    Boolean engnat;
    Constants.Hand hand;
    Constants.married married;
    Constants.Major major;

    public Constants.Major getMajor() {
        return major;
    }

    public void setMajor(Constants.Major major) {
        this.major = major;
    }

    public UserModel(String name, String no, String email) {
        this.name = name;
        this.no = no;
        this.email = email;
    }

    public UserModel(EduStatus education,Gender gender,Constants.Major major, Constants.married married,String name, String no, UrbanStatus urban) {
        this.education = education;
        this.gender = gender;
        this.major = major;
        this.married = married;
        this.name = name;
        this.no = no;
        this.urban = urban;
    }

    public UserModel(String name, String no) {
        this.name = name;
        this.no = no;
    }

    public UserModel(String name, String no, EduStatus education, UrbanStatus urban, Gender gender, Boolean engnat, Constants.Hand hand, Constants.married married,Constants.Major major) {
        this.name = name;
        this.no = no;
        this.education = education;
        this.urban = urban;
        this.gender = gender;
        this.engnat = engnat;
        this.hand = hand;
        this.married = married;
        this.major = major;
    }

    public EduStatus getEducation() {
        return education;
    }

    public void setEducation(EduStatus education) {
        this.education = education;
    }

    public UrbanStatus getUrban() {
        return urban;
    }

    public void setUrban(UrbanStatus urban) {
        this.urban = urban;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Boolean getEngnat() {
        return engnat;
    }

    public void setEngnat(Boolean engnat) {
        this.engnat = engnat;
    }

    public Constants.Hand getHand() {
        return hand;
    }

    public void setHand(Constants.Hand hand) {
        this.hand = hand;
    }

    public Constants.married getMarried() {
        return married;
    }

    public void setMarried(Constants.married married) {
        this.married = married;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}