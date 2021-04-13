package com.zhanziwei.seckill.dao;

import com.zhanziwei.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

public interface SuccessKilledDao {
    int insertSuccessKilled(@Param("seckillId") Long secKillId, @Param("userName") String userName);
    SuccessKilled queryByIdWithSecKill(@Param("seckillId") Long secKillId,@Param("userName") String userName);
}
