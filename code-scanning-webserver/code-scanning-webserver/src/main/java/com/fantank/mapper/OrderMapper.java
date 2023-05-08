package com.fantank.mapper;

import com.fantank.pojo.Order;

public interface OrderMapper {
    Order getVehicleNumberByOrderNumber(String orderNumber);
}
