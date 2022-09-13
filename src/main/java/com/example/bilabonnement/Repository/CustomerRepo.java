package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepo {

    @Autowired
    JdbcTemplate template;

    public List<Customer> fetchAllCustomer(){
        String sql = "SELECT * FROM customers";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return template.query(sql, rowMapper);
    }
    public void addCustomer(Customer customer){
        String sql = "INSERT INTO customers (first_name, last_name, address, email, phone, ssn, zip_code) " +
                "VALUES(?,?,?,?,?,?,?)";
        template.update(sql, customer.getFirst_name(), customer.getLast_name(),
                customer.getAddress(), customer.getEmail(), customer.getPhone(),
                customer.getSsn(), customer.getZip_code());
    }
    public void editCustomer(Customer customer){
        String sql = "UPDATE customers " +
                "Set first_name = ?, last_name = ?, address = ?, email = ?, phone = ?, ssn = ?, zip_code = ? " +
                "WHERE customer_id = ?";
        template.update(sql, customer.getFirst_name(), customer.getLast_name(),
                customer.getAddress(), customer.getEmail(), customer.getPhone(),
                customer.getSsn(), customer.getZip_code(),
                customer.getCustomer_id());
    }
    public Customer findCustomerById(int id){
        String sql = "SELECT * FROM customers WHERE customer_id = ?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        Customer customer = template.queryForObject(sql, rowMapper, id);
        return customer;
    }
    public Boolean deleteCustomer(int id){
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        return template.update(sql, id) > 0;
    }
}
