package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.CarReport;
import com.example.bilabonnement.Model.CarReportItem;
import com.example.bilabonnement.Repository.CarReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarReportService {

    @Autowired
    CarReportRepo carReportRepo;

    public List<CarReport> showReports() {
        return carReportRepo.showReports();
    }

    public List<CarReportItem> fetchAllCarReportItems() {
        return carReportRepo.fetchAllCarReportItems();
    }

    public int getReportIdByCreatingReport(int id) {
        return carReportRepo.getReportIdByCreatingReport(id);
    }

    public void createReportItem(CarReport carReport) {
        carReportRepo.createReportItem(carReport);
    }

    public CarReport findCarReportById(int id) {
        return carReportRepo.findCarReportById(id);
    }

    public void editCarReport(CarReport carReport) {
        carReportRepo.editCarReport(carReport);
    }
}