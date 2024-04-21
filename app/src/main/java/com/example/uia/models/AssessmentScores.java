package com.example.uia.models;

public class AssessmentScores {
    private int realistic;
    private int investigative;
    private int artistic;
    private int social;
    private int enterprising;
    private int conventional;

    public AssessmentScores(int realistic, int investigative, int artistic, int social, int enterprising, int conventional) {
        this.realistic = realistic;
        this.investigative = investigative;
        this.artistic = artistic;
        this.social = social;
        this.enterprising = enterprising;
        this.conventional = conventional;
    }

    public int getRealistic() {
        return realistic;
    }

    public void setRealistic(int realistic) {
        this.realistic = realistic;
    }

    public int getInvestigative() {
        return investigative;
    }

    public void setInvestigative(int investigative) {
        this.investigative = investigative;
    }

    public int getArtistic() {
        return artistic;
    }

    public void setArtistic(int artistic) {
        this.artistic = artistic;
    }

    public int getSocial() {
        return social;
    }

    public void setSocial(int social) {
        this.social = social;
    }

    public int getEnterprising() {
        return enterprising;
    }

    public void setEnterprising(int enterprising) {
        this.enterprising = enterprising;
    }

    public int getConventional() {
        return conventional;
    }

    public void setConventional(int conventional) {
        this.conventional = conventional;
    }
}
