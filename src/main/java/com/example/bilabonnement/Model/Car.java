package com.example.bilabonnement.Model;

import java.util.Locale;

public class Car implements Comparable<Car> {
    //Fields
    private int car_number;
    private String chassis_number;
    private String brand;
    private String model;
    private String fuel_type;
    private String gear_type;
    private int odometer;
    private int scrap_price;
    private int registration_fee;
    private String co2_emission;
    private String mileage;
    private String status;
    private int base_price = 3000; //dummy data until we get the value through subscription id.

    //Features
    private String radio;
    private String bluetooth;
    private String headlights;
    private String rims;
    private String airbags;
    private String speakers;
    private String touchscreen;
    private String aircondition;
    private String apple_carplay;
    private String active_safety_break;
    private String remote_lock;
    private String electric_windows;
    private String electronic_stability_program;
    private String speed_pilot;
    private String heating_adjustable_seats;
    private String hill_assist;
    private String fog_lights;
    private String leather_steering_wheel;
    private String parking_sensor;
    private String electric_adjustable_side_mirror;
    private String alarm;
    private String usb;
    private String lane_departure_warning;

    //Default Constructor so Rowmapper can target the class
    public Car() {
    }

    //Contructor for all fields
    public Car(int car_number, String chassis_number,
               String brand, String model, String fuel_type,
               String gear_type, int odometer, int scrap_price,
               int registration_fee, String co2_emission,
               String mileage, String status, String radio,
               String bluetooth, String headlights, String rims,
               String airbags, String speakers, String touchscreen,
               String aircondition, String apple_carplay,
               String active_safety_break, String remote_lock,
               String electric_windows, String electronic_stability_program,
               String speed_pilot, String heating_adjustable_seats,
               String hill_assist, String fog_lights,
               String leather_steering_wheel, String parking_sensor,
               String electric_adjustable_side_mirror, String alarm,
               String usb, String lane_departure_warning) {
        this.car_number = car_number;
        this.chassis_number = chassis_number;
        this.brand = brand;
        this.model = model;
        this.fuel_type = fuel_type;
        this.gear_type = gear_type;
        this.odometer = odometer;
        this.scrap_price = scrap_price;
        this.registration_fee = registration_fee;
        this.co2_emission = co2_emission;
        this.mileage = mileage;
        this.status = status;
        this.radio = radio;
        this.bluetooth = bluetooth;
        this.headlights = headlights;
        this.rims = rims;
        this.airbags = airbags;
        this.speakers = speakers;
        this.touchscreen = touchscreen;
        this.aircondition = aircondition;
        this.apple_carplay = apple_carplay;
        this.active_safety_break = active_safety_break;
        this.remote_lock = remote_lock;
        this.electric_windows = electric_windows;
        this.electronic_stability_program = electronic_stability_program;
        this.speed_pilot = speed_pilot;
        this.heating_adjustable_seats = heating_adjustable_seats;
        this.hill_assist = hill_assist;
        this.fog_lights = fog_lights;
        this.leather_steering_wheel = leather_steering_wheel;
        this.parking_sensor = parking_sensor;
        this.electric_adjustable_side_mirror = electric_adjustable_side_mirror;
        this.alarm = alarm;
        this.usb = usb;
        this.lane_departure_warning = lane_departure_warning;
    }

    //Constructor for all fields except features

    public Car(int car_number, String chassis_number, String brand,
               String model, String fuel_type, String gear_type,
               int odometer, int scrap_price, int registration_fee,
               String co2_emission, String mileage, String status) {
        this.car_number = car_number;
        this.chassis_number = chassis_number;
        this.brand = brand;
        this.model = model;
        this.fuel_type = fuel_type;
        this.gear_type = gear_type;
        this.odometer = odometer;
        this.scrap_price = scrap_price;
        this.registration_fee = registration_fee;
        this.co2_emission = co2_emission;
        this.mileage = mileage;
        this.status = status;
    }


    //Constructor for features
    public Car(String radio, String bluetooth, String headlights,
               String rims, String airbags, String speakers,
               String touchscreen, String aircondition,
               String apple_carplay, String active_safety_break,
               String remote_lock, String electric_windows,
               String electronic_stability_program,
               String speed_pilot, String heating_adjustable_seats,
               String hill_assist, String fog_lights,
               String leather_steering_wheel, String parking_sensor,
               String electric_adjustable_side_mirror, String alarm,
               String usb, String lane_departure_warning) {
        this.radio = radio;
        this.bluetooth = bluetooth;
        this.headlights = headlights;
        this.rims = rims;
        this.airbags = airbags;
        this.speakers = speakers;
        this.touchscreen = touchscreen;
        this.aircondition = aircondition;
        this.apple_carplay = apple_carplay;
        this.active_safety_break = active_safety_break;
        this.remote_lock = remote_lock;
        this.electric_windows = electric_windows;
        this.electronic_stability_program = electronic_stability_program;
        this.speed_pilot = speed_pilot;
        this.heating_adjustable_seats = heating_adjustable_seats;
        this.hill_assist = hill_assist;
        this.fog_lights = fog_lights;
        this.leather_steering_wheel = leather_steering_wheel;
        this.parking_sensor = parking_sensor;
        this.electric_adjustable_side_mirror = electric_adjustable_side_mirror;
        this.alarm = alarm;
        this.usb = usb;
        this.lane_departure_warning = lane_departure_warning;
    }

    //Getters & Setters
    public int getCar_number() {
        return car_number;
    }

    public void setCar_number(int car_number) {
        this.car_number = car_number;
    }

    public String getChassis_number() {
        return chassis_number;
    }

    public void setChassis_number(String chassis_number) {
        this.chassis_number = chassis_number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public String getGear_type() {
        return gear_type;
    }

    public void setGear_type(String gear_type) {
        this.gear_type = gear_type;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public int getScrap_price() {
        return scrap_price;
    }

    public void setScrap_price(int scrap_price) {
        this.scrap_price = scrap_price;
    }

    public int getRegistration_fee() {
        return registration_fee;
    }

    public void setRegistration_fee(int registration_fee) {
        this.registration_fee = registration_fee;
    }

    public String getCo2_emission() {
        return co2_emission;
    }

    public void setCo2_emission(String co2_emission) {
        this.co2_emission = co2_emission;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getStatus() {
        return status;
    }

    public int getBase_price() {
        return base_price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getHeadlights() {
        return headlights;
    }

    public void setHeadlights(String headlights) {
        this.headlights = headlights;
    }

    public String getRims() {
        return rims;
    }

    public void setRims(String rims) {
        this.rims = rims;
    }

    public String getAirbags() {
        return airbags;
    }

    public void setAirbags(String airbags) {
        this.airbags = airbags;
    }

    public String getSpeakers() {
        return speakers;
    }

    public void setSpeakers(String speakers) {
        this.speakers = speakers;
    }

    public String getTouchscreen() {
        return touchscreen;
    }

    public void setTouchscreen(String touchscreen) {
        this.touchscreen = touchscreen;
    }

    public String getAircondition() {
        return aircondition;
    }

    public void setAircondition(String aircondition) {
        this.aircondition = aircondition;
    }

    public String getApple_carplay() {
        return apple_carplay;
    }

    public void setApple_carplay(String apple_carplay) {
        this.apple_carplay = apple_carplay;
    }

    public String getActive_safety_break() {
        return active_safety_break;
    }

    public void setActive_safety_break(String active_safety_break) {
        this.active_safety_break = active_safety_break;
    }

    public String getRemote_lock() {
        return remote_lock;
    }

    public void setRemote_lock(String remote_lock) {
        this.remote_lock = remote_lock;
    }

    public String getElectric_windows() {
        return electric_windows;
    }

    public void setElectric_windows(String electric_windows) {
        this.electric_windows = electric_windows;
    }

    public String getElectronic_stability_program() {
        return electronic_stability_program;
    }

    public void setElectronic_stability_program(String electronic_stability_program) {
        this.electronic_stability_program = electronic_stability_program;
    }

    public String getSpeed_pilot() {
        return speed_pilot;
    }

    public void setSpeed_pilot(String speed_pilot) {
        this.speed_pilot = speed_pilot;
    }

    public String getHeating_adjustable_seats() {
        return heating_adjustable_seats;
    }

    public void setHeating_adjustable_seats(String heating_adjustable_seats) {
        this.heating_adjustable_seats = heating_adjustable_seats;
    }

    public String getHill_assist() {
        return hill_assist;
    }

    public void setHill_assist(String hill_assist) {
        this.hill_assist = hill_assist;
    }

    public String getFog_lights() {
        return fog_lights;
    }

    public void setFog_lights(String fog_lights) {
        this.fog_lights = fog_lights;
    }

    public String getLeather_steering_wheel() {
        return leather_steering_wheel;
    }

    public void setLeather_steering_wheel(String leather_steering_wheel) {
        this.leather_steering_wheel = leather_steering_wheel;
    }

    public String getParking_sensor() {
        return parking_sensor;
    }

    public void setParking_sensor(String parking_sensor) {
        this.parking_sensor = parking_sensor;
    }

    public String getElectric_adjustable_side_mirror() {
        return electric_adjustable_side_mirror;
    }

    public void setElectric_adjustable_side_mirror(String electric_adjustable_side_mirror) {
        this.electric_adjustable_side_mirror = electric_adjustable_side_mirror;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    public String getUsb() {
        return usb;
    }

    public void setUsb(String usb) {
        this.usb = usb;
    }

    public String getLane_departure_warning() {
        return lane_departure_warning;
    }

    public void setLane_departure_warning(String lane_departure_warning) {
        this.lane_departure_warning = lane_departure_warning;
    }
    //Overrides defalult compareTo and identifies the natural order
    // of the instances of the Car class.
    @Override
    public int compareTo(Car other) {
        //Using String object CompareTo
        return this.brand.toLowerCase().compareTo(other.brand.toLowerCase());
    }

}

