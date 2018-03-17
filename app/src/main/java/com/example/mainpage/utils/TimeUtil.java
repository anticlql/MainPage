package com.example.mainpage.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 刘青林 on 2018/3/17.
 */

public class TimeUtil {

    //获取系统时间戳
    public static String getTime() {
        long time = System.currentTimeMillis()/1000;//获取系统时间的10位的时间戳
        String str = String.valueOf(time);
        return str;
    }

    public static String getCurrentDate() {
        Date d = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        return sf.format(d);
    }

    //时间戳转换日期
    public static String timetodate(String time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.valueOf(time));
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");//这里的格式可换"yyyy年-MM月dd日-HH时mm分ss秒"等等格式
        String date = sf.format(calendar.getTime());
        return date;
    }

    //将时间转换为时间戳
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }
}
