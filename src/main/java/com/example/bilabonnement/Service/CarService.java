package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.Repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepo carRepo;

    public List<Car> showCars() {
        return carRepo.showCars();
    }

    public List<Car> showAvailableCars() {
        return carRepo.showAvailableCars();
    }

    public List<Car> showUnavailableCars() {
        return carRepo.showUnavailableCars();
    }

    public List<Car> searchInCars(String search){
        return carRepo.searchInCars(search);
    }

    public int createCar(Car car) {
        return carRepo.createCar(car);
    }

    public Car findCarByNumber(int id){ return carRepo.findCarByNumber(id);}

    public void editCar(int id, Car car) {
        carRepo.editCar(id, car);
    }

    public Boolean deleteCar(int id) {
        return carRepo.deleteCar(id);
    }

    public void editCarOdometer(int id, int odometer) {
        carRepo.editCarOdometer(id, odometer);
    }
}
