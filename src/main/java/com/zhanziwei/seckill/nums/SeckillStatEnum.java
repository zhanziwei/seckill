package com.zhanziwei.seckill.nums;

public enum SeckillStatEnum {
    END(0,"秒杀结束"),
    SUCCESS(1,"秒杀成功"),
    SUCCESS_LOGIN(2,"登录成功"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATE_REWRITE(-3,"数据篡改");
    private int state;
    private String info;

    SeckillStatEnum(int i, String info) {
        this.state = i;
        this.info = info;
    }

    public int getState() {
        return state;
    }

    public String getInfo() {
        return info;
    }

    public static SeckillStatEnum stateOf(int index) {
        for(SeckillStatEnum state : values()) {
            if(state.getState() == index) return state;
        }
        return null;
    }
}
