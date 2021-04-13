package com.zhanziwei.seckill.dao;

import com.zhanziwei.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
    @Autowired
    private SeckillDao seckillDao;
    @Test
    public void queryAll() throws Exception{
        List<Seckill> seckills = seckillDao.queryAll();
        for(Seckill s:seckills) {
            System.out.println(s.getName());
        }
    }

    @Test
    public void queryById() {
        long seckillId=1000;
        Seckill seckill=seckillDao.queryById(seckillId);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void reduceNumber() {
        long seckillId = 1000;
        Date date = new Date();
        int updateCount = seckillDao.reduceNumber(seckillId,date);
        System.out.println(updateCount);
    }
}