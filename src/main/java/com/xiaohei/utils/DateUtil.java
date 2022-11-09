package com.xiaohei.utils;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理工具
 *
 * @Author: Xiaohei
 * @CreateTime: 2022/11/9 22:27
 */
public class DateUtil {

    /**
     * 时间戳->对应格式
     *
     * @param time 时间戳
     * @param pattern 时间格式，例：yyyy-MM-dd HH:mm:ss
     * @return 时间
     */
    public static String timestampToString(long time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = new Date(time);
        return sdf.format(date);
    }

    /**
     * 获取输入时间的当月起始时间
     *
     * @param date 输入时间
     * @return 当月起始时间
     */
    public static long firstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取输入时间的当月截止时间
     *
     * @param date 输入时间
     * @return 当月截止时间
     */
    public static long lastDayOfMonth(Date date) {
        long l = firstDayOfMonth(date);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(l);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.MILLISECOND, -1);
        return cal.getTimeInMillis();
    }

    public static void main(String[] args) {
        long l = lastDayOfMonth(new Date());
        Date date = new Date(l);
        System.out.println(date);
    }
}
