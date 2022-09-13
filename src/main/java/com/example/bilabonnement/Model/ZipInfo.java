package com.example.bilabonnement.Model;

public class ZipInfo {
    //Fields
    private int zip_code;
    private String city;

    //Constructors
    public ZipInfo() {
    }

    public ZipInfo(int zip_code, String city) {
        this.zip_code = zip_code;
        this.city = city;
    }

    //Getters & Setters
    public int getZip_code() {
        return zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
