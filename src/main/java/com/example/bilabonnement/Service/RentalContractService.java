package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.Model.RentalContract;
import com.example.bilabonnement.Repository.CarRepo;
import com.example.bilabonnement.Repository.RentalContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalContractService {
    @Autowired
    RentalContractRepo rentalContractRepo;

    public List<RentalContract> showRentalContracts() {
        return rentalContractRepo.showRentalContracts();
    }

    public void createRentalContract(RentalContract rentalContract, Car car) {
        rentalContractRepo.createRentalContract(rentalContract,car);
    }

    public RentalContract findRentalContractById(int id){ return rentalContractRepo.findRentalContractById(id);}

    public void editRentalContract(int id, RentalContract rentalContract) {
        rentalContractRepo.editRentalContract(id, rentalContract);
    }

    public Boolean deleteRentalContract(int id) {
       return rentalContractRepo.deleteRentalContract(id);
    }

    public void endRentalContract(int id){
      rentalContractRepo.endRentalContract(id);
    }
}
