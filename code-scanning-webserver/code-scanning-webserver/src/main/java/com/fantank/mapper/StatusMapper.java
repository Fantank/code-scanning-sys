package com.fantank.mapper;

public interface StatusMapper {
    Integer getStatusByOrderNumberThroughTicketId(String orderNumber);
}
