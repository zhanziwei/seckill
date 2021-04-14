package com.zhanziwei.seckill.service.impl;


import com.zhanziwei.seckill.dao.SeckillDao;
import com.zhanziwei.seckill.dao.SuccessKilledDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillServiceImp {
    @Autowired
    SeckillDao seckillDao;
    @Autowired
    SuccessKilledDao successKilledDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());
}
