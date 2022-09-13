package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.ZipInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ZipRepo {
    @Autowired
    JdbcTemplate template;

    public List<ZipInfo> fetchAllZipInfo() {
        String sql = "SELECT * FROM zip_info";
        RowMapper<ZipInfo> rowMapper = new BeanPropertyRowMapper<>(ZipInfo.class);
        return template.query(sql, rowMapper);
    }
}
