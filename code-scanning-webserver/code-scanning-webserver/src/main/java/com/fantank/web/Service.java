package com.fantank.web;

import com.fantank.pojo.Order;

import java.util.Map;

public interface Service {
    Map<String,String> addNewOrderData(String orderNumber);
    Integer getStatusByOrderNumberThroughTicketId(String orderNumber);
}
