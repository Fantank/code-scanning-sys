package com.fantank.web;

import com.fantank.pojo.Order;

public interface Dao {
    Order getVehicleNumberByOrderNumber(String orderNumber);

    Integer getStatusByOrderNumberThroughTicketId(String orderNumber);

    Integer updateQueuingMatchingDriverStatus(String orderNumber);
}
