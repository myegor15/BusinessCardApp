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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (image != card.image) return false;
        if (!personName.equals(card.personName)) return false;
        return organisationName.equals(card.organisationName);
    }

    @Override
    public int hashCode() {
        int result = image;
        result = 31 * result + personName.hashCode();
        result = 31 * result + organisationName.hashCode();
        return result;
    }
}
