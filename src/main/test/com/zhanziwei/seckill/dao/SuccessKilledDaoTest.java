package com.zhanziwei.seckill.dao;

import com.zhanziwei.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {
    @Autowired
    private SuccessKilledDao successKilledDao;
    @Test
    public void insertSuccessKilled() {
        long seckillId = 1003;
        String user_name="tom";
        int insertCount = successKilledDao.insertSuccessKilled(seckillId, user_name);
        System.out.println("insertCount="+insertCount);
    }

    @Test
    public void queryByIdWithSecKill() {
        long seckillId = 1000;
        SuccessKilled insertCount = successKilledDao.queryByIdWithSecKill(seckillId, "tom");
        System.out.println(insertCount.toString());
        System.out.println(insertCount.getSeckill());
    }
}