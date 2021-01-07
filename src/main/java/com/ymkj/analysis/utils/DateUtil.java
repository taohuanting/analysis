package com.ymkj.analysis.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 功能描述：
 *
 * @author: 谢浩
 * @date create in 19-9-10 下午10:04
 */
public class DateUtil {

    public static final String DATE = "yyyy-MM-dd";
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public DateUtil() {
    }

    public static void main(String[] args) {
        addDate((Date)null, 1);
    }

    public static String getCurrentTime() {
        return (new SimpleDateFormat(DATE_TIME)).format(new Date());
    }

    public static Date addDate(Date date, int days) {
        SimpleDateFormat format = new SimpleDateFormat(DATE);
        if (date == null) {
            date = new Date();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, days);
        date = cal.getTime();
        System.out.println(days + " days after(or before) is " + format.format(date));
        return date;
    }

    public static Date parseDate(String date, String formatStr) {
        try {
            SimpleDateFormat format;
            if (formatStr != null && !"".equals(formatStr)) {
                format = new SimpleDateFormat(formatStr);
            } else {
                format = new SimpleDateFormat(DATE);
            }

            return format.parse(date);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return new Date();
        }
    }

    public static String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME);
        if (date == null) {
            date = new Date();
        }

        return format.format(date);
    }

    public static String formatDate(Date date, String formatStr) {
        if (date == null) {
            date = new Date();
        }

        if (formatStr == null) {
            formatStr = DATE_TIME;
        }

        return (new SimpleDateFormat(formatStr)).format(date);
    }
}
