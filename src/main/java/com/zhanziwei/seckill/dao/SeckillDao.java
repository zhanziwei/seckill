package com.zhanziwei.seckill.dao;

import com.zhanziwei.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface SeckillDao {

    List<Seckill> queryAll();
    Seckill queryById(@Param("seckillId") long seckillId);
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);
}
