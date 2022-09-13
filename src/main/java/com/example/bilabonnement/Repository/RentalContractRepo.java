package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.Model.RentalContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RentalContractRepo {
    @Autowired
    JdbcTemplate template;

    public List<RentalContract> showRentalContracts(){
        String sql = "SELECT *" +
                "FROM rental_contracts";
        RowMapper<RentalContract> rowMapper = new BeanPropertyRowMapper<>(RentalContract.class);
        return template.query(sql, rowMapper);
    }

    public void createRentalContract(RentalContract rentalContract, Car car){
        String sql = "INSERT INTO rental_contracts (car_number,customer_id,start_odometer,max_km,start_date,end_date,state) " +
                "VALUES(?,?,?,?,?,?,?)";
        System.out.println(template.update(sql,
                car.getCar_number(), rentalContract.getCustomer_id(), car.getOdometer(),rentalContract.getMax_km(),rentalContract.getStart_date(),rentalContract.getEnd_date(),"aktiv"));


    }

    public RentalContract findRentalContractById(int id){
        String sql = "SELECT * FROM rental_contracts WHERE contract_id = ?";
        RowMapper<RentalContract> rowMapper = new BeanPropertyRowMapper<>(RentalContract.class);
        RentalContract rentalContract = template.queryForObject(sql, rowMapper, id);
        return rentalContract;
    }

    public void editRentalContract(int id, RentalContract rentalContract){
        String sql = "UPDATE rental_contracts " +
                "Set car_number = ?, customer_id = ?, " +
                "start_odometer = ?, max_km = ?, " +
                "start_date = ?, end_date = ?, state = ? " +
                "WHERE contract_id = ?";
        template.update(sql,
                rentalContract.getCar_number(), rentalContract.getCustomer_id(),
                rentalContract.getStart_odometer(),rentalContract.getMax_km(),
                rentalContract.getStart_date(),rentalContract.getEnd_date(),
                rentalContract.getState());
    }

    public Boolean deleteRentalContract(int id){
        String sql = "DELETE FROM rental_contracts WHERE contract_id = ?";
        Boolean deleted = template.update(sql, id) > 0;
        if(deleted){
            sql = "DELETE FROM car_reports WHERE contract_id = ?";
            template.update(sql,id);
        }
        return deleted;
    }
    public void endRentalContract(int id){//sets contract state to ended
        String sql = "UPDATE rental_contracts SET state = 'afsluttet' WHERE contract_id = ?";
        template.update(sql, id);

    }


}
