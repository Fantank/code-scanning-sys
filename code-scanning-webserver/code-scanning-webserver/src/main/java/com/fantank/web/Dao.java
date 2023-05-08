package com.fantank.web;

import com.fantank.pojo.Order;
import org.springframework.stereotype.Repository;

public interface Dao {
    Order getVehicleNumberByOrderNumber(String orderNumber);
}
