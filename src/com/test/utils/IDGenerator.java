package com.test.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2017/5/4.
 */
public class IDGenerator {
    public static String getPrimaryKey(){
        return UUID.randomUUID().toString();
    }
    public static String getOrderNum(){
        //使用当前日期+毫秒值作为订单号
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date) + System.currentTimeMillis();
    }
}
