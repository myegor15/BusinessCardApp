package com.melnichuk.businesscardsapp.pojo;

public class Card {

    private int image;
    private String name;
    private String phoneNum1;
    private String phoneNum2;
    private String fax;
    private String email;
    private String company;
    private String profession;
    private String address;
    private String web;
    private String facebook;
    private String twitter;
    private String instagram;

    public Card() {
    }

    public Card(String name, String company) {
        this.name = name;
        this.company = company;
    }

    public Card(int image, String name, String company) {
        this.image = image;
        this.name = name;
        this.company = company;
    }

    public Card(int image, String name, String phoneNum1, String phoneNum2,
                String fax, String email, String company, String profession,
                String address, String web, String facebook, String twitter, String instagram) {
        this.image = image;
        this.name = name;
        this.phoneNum1 = phoneNum1;
        this.phoneNum2 = phoneNum2;
        this.fax = fax;
        this.email = email;
        this.company = company;
        this.profession = profession;
        this.address = address;
        this.web = web;
        this.facebook = facebook;
        this.twitter = twitter;
        this.instagram = instagram;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum1() {
        return phoneNum1;
    }

    public void setPhoneNum1(String phoneNum1) {
        this.phoneNum1 = phoneNum1;
    }

    public String getPhoneNum2() {
        return phoneNum2;
    }

    public void setPhoneNum2(String phoneNum2) {
        this.phoneNum2 = phoneNum2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }
}