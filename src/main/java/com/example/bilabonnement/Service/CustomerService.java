package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Customer;
import com.example.bilabonnement.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    public List<Customer> fetchAllCustomers(){
        return customerRepo.fetchAllCustomer();
    }
    public void createCustomer(Customer customer){
        customerRepo.addCustomer(customer);
    }
    public void editCustomer(Customer customer){
        customerRepo.editCustomer(customer);
    }
    public Customer findCustomerById(int id){
        return customerRepo.findCustomerById(id);
    }
    public Boolean deleteCustomer(int id){
        return customerRepo.deleteCustomer(id);
    }
}
