package com.example.bilabonnement.Model;

public class RentalContract {
    //Fields
    int contract_id;
    int car_number;
    int customer_id;
    int start_odometer;
    int max_km;
    String start_date;
    String end_date;
    String state;

    //Constructors
    public RentalContract(){}

    public RentalContract(int contract_id, int car_number, int customer_id, int start_odometer, int max_km, String start_date, String end_date, String state) {
        this.contract_id = contract_id;
        this.car_number = car_number;
        this.customer_id = customer_id;
        this.start_odometer = start_odometer;
        this.max_km = max_km;
        this.start_date = start_date;
        this.end_date = end_date;
        this.state = state;
    }

    //Getters & Setters

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public int getCar_number() {
        return car_number;
    }

    public void setCar_number(int car_number) {
        this.car_number = car_number;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getStart_odometer() {
        return start_odometer;
    }

    public void setStart_odometer(int start_odometer) {
        this.start_odometer = start_odometer;
    }

    public int getMax_km() {
        return max_km;
    }

    public void setMax_km(int max_km) {
        this.max_km = max_km;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
