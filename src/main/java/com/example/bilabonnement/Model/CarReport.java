package com.example.bilabonnement.Model;

import java.util.ArrayList;
import java.util.List;

public class CarReport {
    //Fields
    private int report_id;
    private int contract_id;
    private double total_price;
    private List<CarReportItem> items = new ArrayList<>();

    //Constructors
    public CarReport() {
    }

    public CarReport(int report_id, int contract_id, int total_price, List<CarReportItem> items) {
        this.report_id = report_id;
        this.contract_id = contract_id;
        this.total_price = total_price;
        this.items = items;
    }

    //Getters & Setters
    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public List<CarReportItem> getItems() {
        return items;
    }

    public void setItems(List<CarReportItem> items) {
        this.items = items;
    }

    public void addItems(CarReportItem items) {
        this.items.add(items);
    }

    @Override
    public String toString() {
        return "CarReport{" +
                "report_id=" + report_id +
                ", contract_id=" + contract_id +
                ", items=" + items +
                '}';
    }
}
