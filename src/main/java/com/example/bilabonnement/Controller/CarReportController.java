package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.*;
import com.example.bilabonnement.Service.CarReportService;
import com.example.bilabonnement.Service.CarService;
import com.example.bilabonnement.Service.CustomerService;
import com.example.bilabonnement.Service.RentalContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarReportController {

    @Autowired
    CarReportService carReportService;

    @Autowired
    RentalContractService rentalContractService;

    @Autowired
    CarService carService;

    @Autowired
    CustomerService customerService;

    @GetMapping("/manageReports")
    public String manageCarReports(Model model) {
        // Car Report
        List<CarReport> reportList = carReportService.showReports();
        // Car Report Item
        List<CarReportItem> carReportItems = carReportService.fetchAllCarReportItems();
        for (int i = 0; i < reportList.size(); i++) {
            for (int j = 0; j < carReportItems.size(); j++) {
                // Checks if report_id in Car Report is equals to report_id in Car Report Item is true
                if (reportList.get(i).getReport_id() == carReportItems.get(j).getReport_id()) {
                    // Calls addItems from report where it adds carReportItems to the field "items" in CarReport class
                    reportList.get(i).addItems(carReportItems.get(j));
                }
            }
        }
        // Calculate total price
        double[] totalPrices = new double[reportList.size()];
        for (int i = 0; i < reportList.size(); i++) {
            for (int j = 0; j < reportList.get(i).getItems().size(); j++) {
                totalPrices[i] += reportList.get(i).getItems().get(j).getPrice();
            }
            reportList.get(i).setTotal_price(totalPrices[i]);
        }

        // Model
        model.addAttribute("carReports", reportList);

        return "carReport/manageReports";
    }

    @GetMapping("/createCarReport/{id}")
    public String createCarReport(@PathVariable("id") int contractId, Model model) {
        //Find rental contract to get old odometer, max km and car number
        RentalContract rentalContract = rentalContractService.findRentalContractById(contractId);
        //Find car to get new odometer
        Car car = carService.findCarByNumber(rentalContract.getCar_number());
        //Calculate exceeded kilometers
        int expectedKm = rentalContract.getStart_odometer() + rentalContract.getMax_km();
        int newKm = car.getOdometer();
        int exceededKm = 0;
        double exceededKmPrice = 0.0;
        if (expectedKm < newKm) { // Checks if the expected kilometers are smaller than the new odometer in car
            exceededKm = newKm - expectedKm;
            exceededKmPrice = exceededKm * 0.75; // exceededKm multiplied with 0.75 kr./km.
        }
        // model.addAttribute
        model.addAttribute("contractId", contractId);
        model.addAttribute("exceededKm", exceededKm);
        model.addAttribute("exceededKmPrice", exceededKmPrice);
        return "carReport/create";
    }

    @PostMapping("/createCarReport")
    public String create(@ModelAttribute CarReport carReport, @ModelAttribute CarReportItem carReportItem, @RequestParam(name = "prices") String prices) {
        // Splitting the String when there is a ","
        String[] type = carReportItem.getType().split(",");
        String[] description = carReportItem.getDescription().split(",");
        String[] price = prices.split(",");

        // Creating Car Report and gets the id of Car Report
        int reportId = carReportService.getReportIdByCreatingReport(carReport.getContract_id());

        // Create CarReportItem objects and add them to CarReport items list
        for (int i = 0; i < type.length; i++) {
            // Creates CarReportItem object
            CarReportItem tempCarReportItem = new CarReportItem(0, reportId, type[i], description[i], Double.parseDouble(price[i]));
            // Adds tempCarReportItem to carReport items list
            carReport.addItems(tempCarReportItem);
        }

        // Creating Car Report item
        carReportService.createReportItem(carReport);

        return "redirect:/manageReports";
    }

    @GetMapping("/editCarReport/{id}")
    public String edit(@PathVariable("id") int report_id, Model model) {
        model.addAttribute("reports", findCarReportAndItems(report_id, model));
        return "carReport/edit";
    }

    @PostMapping("/editReport")
    public String editCarReport(@ModelAttribute CarReport carReport, @ModelAttribute CarReportItem carReportItem,
                                @RequestParam(name = "prices") String prices, @RequestParam(name = "itemID") String itemID) {
        // Splitting the String when there is a ","
        String[] itemId = itemID.split(",");
        String[] type = carReportItem.getType().split(",");
        String[] description = carReportItem.getDescription().split(",");
        String[] price = prices.split(",");

        // Create CarReportItem objects and add them to CarReport items list
        for (int i = 0; i < type.length; i++) {
            CarReportItem tempCarReportItem = new CarReportItem(Integer.parseInt(itemId[i]),
                    carReportItem.getReport_id(), type[i],
                    description[i], Integer.parseInt(price[i]));
            carReport.addItems(tempCarReportItem);
        }

        // Calls editCarReport method from carReportService to update carReport
        carReportService.editCarReport(carReport);
        return "redirect:/manageReports";
    }

    @GetMapping("/detailCarReport/{id}")
    public String detailCarReport(@PathVariable("id") int report_id, Model model) {
        CarReport report = findCarReportAndItems(report_id, model);
        // Rental contract
        RentalContract rentalContract = rentalContractService.findRentalContractById(report.getContract_id());
        // Car
        Car car = carService.findCarByNumber(rentalContract.getCar_number());
        // Customer
        Customer customer = customerService.findCustomerById(rentalContract.getCustomer_id());
        // Calculate total price
        double totalPrice = 0.0;
        for (int i = 0; i < report.getItems().size(); i++) {
            totalPrice += report.getItems().get(i).getPrice();
        }
        // model.addAttribute
        model.addAttribute("reports", report);
        model.addAttribute("contract", rentalContract);
        model.addAttribute("car", car);
        model.addAttribute("customer", customer);
        model.addAttribute("totalPrice", totalPrice);
        return "carReport/detail";
    }

    private CarReport findCarReportAndItems(@PathVariable("id") int report_id, Model model) {
        CarReport report = carReportService.findCarReportById(report_id);
        // Car Report Item
        List<CarReportItem> carReportItems = carReportService.fetchAllCarReportItems();
        for (int j = 0; j < carReportItems.size(); j++) {
            if (report.getReport_id() == carReportItems.get(j).getReport_id()) {
                report.addItems(carReportItems.get(j));
            }
        }
        return report;
    }
}
