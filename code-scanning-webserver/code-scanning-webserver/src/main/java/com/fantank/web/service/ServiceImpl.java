package com.fantank.web.service;

import com.fantank.pojo.Order;
import com.fantank.web.Dao;
import com.fantank.web.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    @Autowired
    private Dao dao;
    @Override
    public Map<String,String> addNewOrderData(String orderNumber) {
        Map<String,String> res = new HashMap<>();
        Order order = dao.getVehicleNumberByOrderNumber(orderNumber);
        System.out.println("service "+ order);
        if(order == null) return null;
        res.put("vehicle_number",order.getVehicleNumber());
        res.put("time_in",order.getTimeIn());
        return res;
    }
}
