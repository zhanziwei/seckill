package com.zhanziwei.seckill.entity;

import java.util.Date;

public class SuccessKilled {
    Long seckillId;
    String userName;
    Short state;
    Date create_time;
    Seckill seckill;

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userName='" + userName + '\'' +
                ", state=" + state +
                ", create_time=" + create_time +
                '}';
    }
}
