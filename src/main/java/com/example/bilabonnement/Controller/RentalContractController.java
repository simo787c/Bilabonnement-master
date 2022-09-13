package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.Model.CarReport;
import com.example.bilabonnement.Model.Customer;
import com.example.bilabonnement.Model.RentalContract;
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
public class RentalContractController {
    @Autowired
    RentalContractService rentalContractService;

    @Autowired
    CarService carService;

    @Autowired
    CarReportService carReportService;

    @Autowired
    CustomerService customerService;

    @GetMapping("/manageRentalContracts")
    public String manageRentalContracts(Model model) { //retrieves and binds contracts and reports to model
        List<RentalContract> rentalContractList = rentalContractService.showRentalContracts();
        model.addAttribute("rentalContracts", rentalContractList);

        List<CarReport> reportList = carReportService.showReports();
        model.addAttribute("reports", reportList);

        return "rentalContract/manageRentalContracts";
    }

    @GetMapping("/createRentalContract/{id}")
    public String create(@PathVariable("id") int car_number, Model model) {
        //retrieve and bind cars and customers
        List<Car> carList = carService.showAvailableCars();
        model.addAttribute("cars", carList);
        List<Customer> customerList = customerService.fetchAllCustomers();
        model.addAttribute("customers", customerList);
        //to get path variable from chosen car
        model.addAttribute("default", car_number);
        //if the user has chosen a car, bind the car to model for posting
        if (car_number != 0) {
            model.addAttribute("car", carService.findCarByNumber(car_number));
        }
        return "rentalContract/create";
    }

    @PostMapping("/createRentalContract")
    public String create(@ModelAttribute RentalContract rentalContract, @ModelAttribute Car car) {//retrieves user input + car and customer ids from model
        //finds chosen car, changes its status to rented and creates contract
        car = carService.findCarByNumber(car.getCar_number());
        car.setStatus("udlejet");
        rentalContractService.createRentalContract(rentalContract, car);
        carService.editCar(car.getCar_number(), car);
        return "redirect:/manageRentalContracts";
    }

    @GetMapping("/editRentalContract/{id}")
    public String edit(@PathVariable("id") int contract_id, Model model) {
        model.addAttribute("rentalContract", rentalContractService.findRentalContractById(contract_id));
        return "rentalContract/edit";
    }

    @PostMapping("/editRentalContract")
    public String edit(@ModelAttribute RentalContract rentalContract) {//retrieves user input and requests to edit
        rentalContractService.editRentalContract(rentalContract.getContract_id(), rentalContract);
        return "redirect:/";
    }

    @GetMapping("/detailRentalContract/{id}")
    public String detail(@PathVariable("id") int contract_id, Model model) {//retrieves contract by id and binds it to model
        RentalContract rentalContract = rentalContractService.findRentalContractById(contract_id);
        Car car = carService.findCarByNumber(rentalContract.getCar_number());
        Customer customer = customerService.findCustomerById(rentalContract.getCustomer_id());
        model.addAttribute("rentalContract", rentalContract);
        model.addAttribute("car", car);
        model.addAttribute("customer", customer);
        return "rentalContract/detail";
    }

    @GetMapping("/deleteRentalContract/{id}")
    public String delete(@PathVariable("id") int contract_id) {//requests delete on id
        setsCarStatusAvailable(contract_id);
        Boolean deleted = rentalContractService.deleteRentalContract(contract_id);
        if (deleted) {
            return "redirect:/manageRentalContracts";
        } else {
            return "redirect:/manageRentalContracts";
        }
    }

    @GetMapping("/endRentalContract")
    public String endContract(@RequestParam(name = "contract_id") int contract_id,
                              @RequestParam(name = "car_number") int car_number,
                              @RequestParam(name = "odometer") int odometer) {//gets ids and user input odometer via form
        rentalContractService.endRentalContract(contract_id);
        setsCarStatusAvailable(contract_id);
        carService.editCarOdometer(car_number, odometer);
        return "redirect:/manageRentalContracts";
    }

    private void setsCarStatusAvailable(int contract_id) {//to change car status when contract is ended/deleted
        Car car = carService.findCarByNumber(rentalContractService.findRentalContractById(contract_id).getCar_number());
        car.setStatus("ikke udlejet");
        carService.editCar(car.getCar_number(), car);
    }
}
