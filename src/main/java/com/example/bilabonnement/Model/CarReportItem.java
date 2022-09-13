package com.example.bilabonnement.Model;

public class CarReportItem {
    private int report_item_id;
    private int report_id;
    private String type;
    private String description;
    private double price;

    public CarReportItem(){}

    public CarReportItem(int report_item_id,int report_id,String type,String description,double price){
        this.report_item_id = report_item_id;
        this.report_id = report_id;
        this.type = type;
        this.description = description;
        this.price = price;
    }

    public int getReport_item_id() {
        return report_item_id;
    }

    public void setReport_item_id(int report_item_id) {
        this.report_item_id = report_item_id;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CarReportItem{" +
                "report_item_id=" + report_item_id +
                ", report_id=" + report_id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
