package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.ZipInfo;
import com.example.bilabonnement.Repository.ZipRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZipService {
    @Autowired
    ZipRepo zipRepo;

    public List<ZipInfo> fetchAllZipInfo(){
        return zipRepo.fetchAllZipInfo();
    }

}
