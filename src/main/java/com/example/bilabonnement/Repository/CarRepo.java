package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepo {

    @Autowired
    JdbcTemplate template;
    //convert rows of new Car instances and maps column values from the database,
    //based on the matching column name that are obtained from the result set meta data
    //to public setters for the corresponding properties.
    //mapping between the columns and the fields are facilitated by them sharing the same name.
    public List<Car> showCars() {
        String sql = "SELECT *" +
                "FROM cars";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        //returns a list of the Car object, by giving the query method a string sql as parameter
        //to create a prepared statement from SQL and a list of arguments to bind to the query,
        //mapping each row to a result object via a RowMapper as parameter.
        return template.query(sql, rowMapper);
    }

    public List<Car> showAvailableCars() {
        String sql = "SELECT *" +
                "FROM cars " +
                "WHERE status = 'ikke udlejet'";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return template.query(sql, rowMapper);
    }

    public List<Car> showUnavailableCars() {
        String sql = "SELECT *" +
                "FROM cars " +
                "WHERE status = 'udlejet'";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return template.query(sql, rowMapper);
    }

    public List<Car> searchInCars(String search){
        String sql = "SELECT *" +
                "FROM cars " +
                "WHERE " +
                "chassis_number LIKE '%"+ search +"%' " +
                " OR brand LIKE '%"+ search +"%' " +
                " OR model LIKE '%"+ search +"%' ";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return template.query(sql, rowMapper);
    }

    public int createCar(Car car) {
        String sqlCreate = "INSERT INTO cars (car_number, chassis_number, brand, model, fuel_type," +
                " gear_type, odometer, scrap_price, registration_fee, co2_emission, mileage, status, " +
                "radio, bluetooth, headlights, rims, airbags, speakers, touchscreen, aircondition," +
                " apple_carplay, active_safety_break, remote_lock, electric_windows," +
                " electronic_stability_program, speed_pilot, heating_adjustable_seats, hill_assist," +
                " fog_lights, leather_steering_wheel, parking_sensor, electric_adjustable_side_mirror," +
                " alarm, usb, lane_departure_warning) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //Issue a single SQL update operation (such as an insert, update or delete statement)
        // via a prepared statement, binding the given arguments.
        return template.update(sqlCreate,
                car.getCar_number(), car.getChassis_number(),
                car.getBrand(), car.getModel(), car.getFuel_type(),
                car.getGear_type(), car.getOdometer(), car.getScrap_price(),
                car.getRegistration_fee(), car.getCo2_emission(),
                car.getMileage(), car.getStatus(), car.getRadio(),
                car.getBluetooth(), car.getHeadlights(), car.getRims(),
                car.getAirbags(), car.getSpeakers(), car.getTouchscreen(),
                car.getAircondition(), car.getApple_carplay(),
                car.getActive_safety_break(), car.getRemote_lock(),
                car.getElectric_windows(), car.getElectronic_stability_program(),
                car.getSpeed_pilot(), car.getHeating_adjustable_seats(),
                car.getHill_assist(), car.getFog_lights(),
                car.getLeather_steering_wheel(), car.getParking_sensor(),
                car.getElectric_adjustable_side_mirror(), car.getAlarm(),
                car.getUsb(), car.getLane_departure_warning());

    }
    //returns single result object that has been mapped via RowMapper,
    //the string SQL to create a prepared statement from SQL
    //and a list of arguments ot bind the query as parameters.
    public Car findCarByNumber(int id) {
        String sql = "SELECT * FROM cars WHERE car_number = ?";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        Car car = template.queryForObject(sql, rowMapper, id);
        return car;
    }

    public void editCar(int id, Car car) {
        String sql = "UPDATE cars " +
                "SET chassis_number = ?, brand = ?, model = ?, fuel_type = ?, gear_type = ?, odometer = ?, scrap_price = ?, registration_fee = ?, co2_emission = ?, mileage = ?, status = ?" +
                "WHERE car_number = ?";
        template.update(sql, car.getChassis_number(), car.getBrand(), car.getModel(), car.getFuel_type(), car.getGear_type(), car.getOdometer(), car.getScrap_price(), car.getRegistration_fee(), car.getCo2_emission(), car.getMileage(), car.getStatus(), car.getCar_number());
    }

    public void editCarOdometer(int id, int odometer) {
        String sql = "UPDATE cars " +
                "SET odometer = ? " +
                "WHERE car_number = ?";
        template.update(sql, odometer, id);
    }

    public Boolean deleteCar(int id) {
        String sql = "DELETE FROM cars WHERE car_number = ?";
        return template.update(sql, id) > 0;
    }
}
