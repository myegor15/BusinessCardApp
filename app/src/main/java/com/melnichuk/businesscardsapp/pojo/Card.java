package com.melnichuk.businesscardsapp.pojo;

public class Card {

    private int image;
    private String personName;
    private String organisationName;

    public Card() {
    }

    public Card(String personName, String organisationName) {
        this.personName = personName;
        this.organisationName = organisationName;
    }

    public Card(int image, String personName, String organisationName) {
        this.image = image;
        this.personName = personName;
        this.organisationName = organisationName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }
}
