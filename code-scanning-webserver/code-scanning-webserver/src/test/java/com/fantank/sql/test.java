package com.fantank.sql;

import com.fantank.mapper.OrderMapper;
import com.fantank.pojo.Order;
import com.fantank.utils.SqlSessionUtil;
import com.fantank.web.Service;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
    @Test
    public void testSql(){
        SqlSession session = SqlSessionUtil.getSqlSession();
        OrderMapper om = session.getMapper(OrderMapper.class);
        Order order = om.getVehicleNumberByOrderNumber("0080058302");
        System.out.println(order);
    }
    @Test
    public void testBean(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        ioc.getBean(Service.class);
    }
}
