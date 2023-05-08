package com.fantank.web.dao;

import com.fantank.mapper.OrderMapper;
import com.fantank.pojo.Order;
import com.fantank.utils.SqlSessionUtil;
import com.fantank.web.Dao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class DaoImpl implements Dao {
    SqlSession session = SqlSessionUtil.getSqlSession();
    @Override
    public Order getVehicleNumberByOrderNumber(String orderNumber) {
         OrderMapper orderMapper = session.getMapper(OrderMapper.class);
         Order order = orderMapper.getVehicleNumberByOrderNumber(orderNumber);
         System.out.println("database " +order);
         return order;
    }
}
