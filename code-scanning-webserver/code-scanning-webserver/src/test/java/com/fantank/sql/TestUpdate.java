package com.fantank.sql;

import com.fantank.mapper.QueuingMapper;
import com.fantank.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class TestUpdate {
    @Test
    public void testUpdate() {
        SqlSession intercourseSession = SqlSessionUtil.getSqlSession("intercourse");
        QueuingMapper mapper = intercourseSession.getMapper(QueuingMapper.class);
        Integer status = mapper.updateMatchingDriveStatus("0080058302");
        System.out.println(status);

        // 关闭SqlSession
        intercourseSession.close();
    }
}
