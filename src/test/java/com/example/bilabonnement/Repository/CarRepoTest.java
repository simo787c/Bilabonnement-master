package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarRepoTest {

    @Autowired
    private CarRepo carRepo;

    @Test
    void deleteCar() {
    }

    @Test
    void carCreated() { //Teste hvorvidt car objekt bliver gemt rigtigt i database.
        Car car = new Car(7, "5884737558434", "Toyota", "IDKModel", "Benzin",
                "Manuelt", 1200, 3200, 2400, "10", "21", "ikke udlejet");
        carRepo.createCar(car);
        Car carTest = carRepo.findCarByNumber(7);
        assertEquals("5884737558434", carTest.getChassis_number());
    }

}