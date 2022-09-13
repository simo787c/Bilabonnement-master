package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Customer;
import com.example.bilabonnement.Model.ZipInfo;
import com.example.bilabonnement.Service.CustomerService;
import com.example.bilabonnement.Service.ZipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    ZipService zipService;

    @GetMapping("/manageCustomer")
    public String manageCustomer(Model model) {
        List<Customer> customerList = customerService.fetchAllCustomers();
        model.addAttribute("customers", customerList);
        return "customer/manageCustomer";
    }

    @GetMapping("/createCustomer")
    public String createCustomer(Model model) {
        List<ZipInfo> zipList = zipService.fetchAllZipInfo();
        model.addAttribute("zipCodes", zipList);
        String[] zip = new String[zipList.size()];
        String[] city = new String[zipList.size()];
        for (int i = 0; i < zipList.size(); i++) {
            zip[i] = String.valueOf(zipList.get(i).getZip_code());
            city[i] = zipList.get(i).getCity();
        }
        model.addAttribute("zips", zip);
        model.addAttribute("cities", city);
        return "customer/create";
    }

    @PostMapping("/createCustomer")
    public String createCustomer(@ModelAttribute Customer customer) {
        customerService.createCustomer(customer);
        return "redirect:/manageCustomer";
    }

    @GetMapping("/editCustomer/{id}")
    public String editCustomer(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerService.findCustomerById(id));
        return "customer/edit";
    }

    @PostMapping("/editCustomer")
    public String editCustomer(@ModelAttribute Customer customer) {
        customerService.editCustomer(customer);
        return "redirect:/manageCustomer";
    }

    @GetMapping("/customerDetail/{id}")
    public String customerDetail(@PathVariable("id") int id, Model model){
        model.addAttribute("customer", customerService.findCustomerById(id));
        return "customer/detail";
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable("id") int id) {
        Boolean deleted = customerService.deleteCustomer(id);
        if (deleted) {
            return "redirect:/manageCustomer";
        } else {
            return "redirect:/manageCustomer";
        }
    }
}
