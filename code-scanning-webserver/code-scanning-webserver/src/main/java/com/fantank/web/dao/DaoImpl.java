package com.fantank.web.dao;

import com.fantank.mapper.OrderMapper;
import com.fantank.mapper.StatusMapper;
import com.fantank.pojo.Order;
import com.fantank.utils.SqlSessionUtil;
import com.fantank.web.Dao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class DaoImpl implements Dao {

    @Override
    public Order getVehicleNumberByOrderNumber(String orderNumber) {
         SqlSession devSession = SqlSessionUtil.getSqlSession("development");
         OrderMapper orderMapper = devSession.getMapper(OrderMapper.class);
         Order order = orderMapper.getVehicleNumberByOrderNumber(orderNumber);
         System.out.println("database " +order);
         return order;
    }

    @Override
    public Integer getStatusByOrderNumberThroughTicketId(String orderNumber) {
        SqlSession devSession = SqlSessionUtil.getSqlSession("development");
        StatusMapper mapper = devSession.getMapper(StatusMapper.class);
        Integer status = mapper.getStatusByOrderNumberThroughTicketId(orderNumber);
        return status;
    }
}
