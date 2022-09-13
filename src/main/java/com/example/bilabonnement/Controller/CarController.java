package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

//Telling Spring, that we are in the Controller class.
@Controller
public class CarController {

    //Autowired uses the Dependency injection pattern implicitly, to create and instance of
    // carService class, without having to write constructor and setter injections.
    @Autowired
    CarService carService;

    //Mapping HTTP GET request onto manageCars.
    //Shortcut for @RequestMapping's get method.
    @GetMapping("/manageCars")
    public String manageCars(Model model) {
        //instanciate a List with Car as its element and equals it carService.showCars.
        List<Car> carList = carService.showCars();
        //Equals int value with the getTotal method in CarController class.
        int total = getTotal(carList);
        //Equals int value with the getTotalPrice method in CarController class.
        int totalPrice = getTotalPrice(carList);
        //binds the return values to a named model attribute, and then exposes it to a web view
        model.addAttribute("total", total);
        model.addAttribute("total_price", totalPrice);
        model.addAttribute("cars", carList);
        return "car/manageCars";
    }

    //Handles template variables in the request URL mapping, and sets then as method parameters.
    @GetMapping("/detail/{id}")
    public String detailCar(@PathVariable("id") int car_number, Model model) {
        model.addAttribute("car", carService.findCarByNumber(car_number));
        return "car/detail";
    }

    @GetMapping("/manageCars/{id}")
    public String sortCars(@PathVariable("id") int choice, Model model) {
        //initialize carlist to null
        List<Car> carList = null;
        //initialize total to 0.
        int total = 0;
        int totalPrice = 0;
        if (choice == 0) {
            carList = carService.showCars();
            //alphabetically sorts carlist on brand
            Collections.sort(carList);
            total = getTotal(carList);
            totalPrice = getTotalPrice(carList);
        } else if (choice == 1) {
            //show only 'udlejet'
            carList = carService.showUnavailableCars();
            total = getTotal(carList);
            totalPrice = getTotalPrice(carList);
        } else if (choice == 2) {
            //show only 'ikke udlejet'
            carList = carService.showAvailableCars();
            total = getTotal(carList);
        }

        model.addAttribute("cars", carList);
        model.addAttribute("choice", choice);
        model.addAttribute("total", total);
        model.addAttribute("total_price", totalPrice);
        return "car/manageCars";
    }
    //gets the total number of cars in carlist
    private int getTotal(List<Car> carList) {
        int total = 0;
        //adds 1 to a cumulative sum in total for each index of carlist.size.
        for (int i = 0; i < carList.size(); i++) {
            total += 1;
        }
        return total;
    }

    private int getTotalPrice(List<Car> carList) {
        int total = 0;
        // Beregne total af biler
        for (int i = 0; i < carList.size(); i++) {
            total += (carList.get(i).getRegistration_fee() + carList.get(i).getBase_price());
        }
        return total;
    }

    // Reads the HTML form data provided by a user and binds it to the request parameter.
    @GetMapping("/manageCars/search/")
    public String searchInCars(@RequestParam("keyword") String keyword, Model model) {
        List<Car> carList = carService.searchInCars(keyword);
        int total = getTotal(carList);
        int totalPrice = getTotalPrice(carList);
        model.addAttribute("total", total);
        model.addAttribute("cars", carList);
        model.addAttribute("total_price", totalPrice);
        return "car/manageCars";
    }

    @GetMapping("/createCar")
    public String create() {
        return "car/create";
    }

    //Uses annotation @ModelAttribute to read data from Car object and assigning it to handler method parameters.
    @PostMapping("/createCar")
    public String create(@ModelAttribute Car car, Model model) {
        int created = carService.createCar(car);
        //create a model to let html see whether it was created, then show alert box
        model.addAttribute("created", created);
        if (created == 0) {
            //failed
            return null;
        } else {
            //successfully creates
            return "redirect:/manageCars";
        }
    }

    @GetMapping("/editCar/{id}")
    public String edit(@PathVariable("id") int car_number, Model model) {
        findCarByNumber(car_number, model);
        return "car/edit";
    }

    @PostMapping("/editCar")
    public String editCar(@ModelAttribute Car car) {
        carService.editCar(car.getCar_number(), car);
        return "redirect:/manageCars";
    }

    private void findCarByNumber(@PathVariable("id") int car_number, Model model) {
        model.addAttribute("car", carService.findCarByNumber(car_number));
    }

    @GetMapping("/deleteCar/{id}")
    public String delete(@PathVariable("id") int car_number) {
        Boolean deleted = carService.deleteCar(car_number);
        if (deleted) {
            return "redirect:/manageCars";
        }
        //Eventuel fejlbesked via chrome/java script. Virker ikke hensigtsmæssigt at redirect selv ved en fejl.
        else {
            return "redirect:/manageCars";
        }
    }
}
