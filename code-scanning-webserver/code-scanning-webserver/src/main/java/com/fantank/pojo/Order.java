package com.fantank.pojo;

import java.time.LocalDateTime;


public class Order {
    private String vehicleNumber;
    private String orderNumber;
    private String timeIn;

    public Order(String vehicleNumber, String orderNumber) {
        this.vehicleNumber = vehicleNumber;
        this.orderNumber = orderNumber;
        this.timeIn = LocalDateTime.now().toString();
    }

    public Order() {
        this.timeIn = LocalDateTime.now().toString();
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", timeIn='" + timeIn + '\'' +
                '}';
    }
}
