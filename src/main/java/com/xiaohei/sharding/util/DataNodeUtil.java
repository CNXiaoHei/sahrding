package com.xiaohei.sharding.util;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: Xiaohei
 * @CreateTime: 2022/11/10 0:41
 */
public class DataNodeUtil {

    public static List<String> getDataNodes() {
        List<String> list = new ArrayList<>();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM");
        String format = sdf.format(date);
        list.add(format);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        for (int i = 0; i < 3; i++) {
            cal.add(Calendar.MONTH, -1);
            Date datePrev = cal.getTime();
            String table = sdf.format(datePrev);
            list.add(table);
        }
        return list;
    }
}
