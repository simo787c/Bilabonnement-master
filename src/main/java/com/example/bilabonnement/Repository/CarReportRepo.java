package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.CarReport;
import com.example.bilabonnement.Model.CarReportItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CarReportRepo {
    @Autowired
    JdbcTemplate template;
    public List<CarReport> showReports() {
        String sql = "SELECT * " +
                "FROM car_reports";
        RowMapper<CarReport> rowMapper = new BeanPropertyRowMapper<>(CarReport.class);
        return template.query(sql, rowMapper);
    }

    public List<CarReportItem> fetchAllCarReportItems() {
        String sqlItems = "SELECT * FROM car_report_items";
        RowMapper<CarReportItem> rowMapperItem = new BeanPropertyRowMapper<>(CarReportItem.class);
        return template.query(sqlItems, rowMapperItem);
    }

    public int getReportIdByCreatingReport(int id) {
        String sqlCreate = "INSERT INTO car_reports (contract_id) " +
                "VALUES(?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sqlCreate, new String[]{"id"});
                        ps.setInt(1, id);
                        return ps;
                    }
                }, keyHolder); // keyHolder.getKey() now contains the generated key

        // Returns int value of the generated id of the created CarReport
        return keyHolder.getKey().intValue();
    }

    public void createReportItem(CarReport carReport) {
        String sqlCreate = "INSERT INTO car_report_items (report_id, type, description, price) " +
                "VALUES(?,?,?,?)";

        for (int i = 0; i < carReport.getItems().size(); i++) {
            template.update(sqlCreate,
                    carReport.getItems().get(i).getReport_id(),
                    carReport.getItems().get(i).getType(),
                    carReport.getItems().get(i).getDescription(),
                    carReport.getItems().get(i).getPrice());
        }
    }

    public CarReport findCarReportById(int id) {
        String sql = "SELECT * FROM car_reports WHERE report_id = ?";
        RowMapper<CarReport> rowMapper = new BeanPropertyRowMapper<>(CarReport.class);
        CarReport carReport = template.queryForObject(sql, rowMapper, id);
        return carReport;
    }

    public void editCarReport(CarReport carReport) {
        String sql = "UPDATE car_report_items " +
                "SET type = ?, description = ?, price = ? " +
                "WHERE report_item_id = ?";

        for (int i = 0; i < carReport.getItems().size(); i++) {
            template.update(sql,
                    carReport.getItems().get(i).getType(),
                    carReport.getItems().get(i).getDescription(),
                    carReport.getItems().get(i).getPrice(),
                    carReport.getItems().get(i).getReport_item_id());
        }
    }
}
