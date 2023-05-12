package com.fantank.sql;

import com.fantank.mapper.StatusMapper;
import com.fantank.utils.SqlSessionUtil;
import com.fantank.web.Dao;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class TestSelect {

    @Test
    public void selectStatus(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession("development");
        StatusMapper mapper = sqlSession.getMapper(StatusMapper.class);
        Integer ticketIdByOrderNumber = mapper.getStatusByOrderNumberThroughTicketId("0080043395");
        System.out.println(ticketIdByOrderNumber);
    }

}
