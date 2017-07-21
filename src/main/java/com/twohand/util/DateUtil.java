package com.twohand.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/25.
 */
public class DateUtil {
    public final static String FORMAT1 = "yyyy-MM--dd";
    public final static String FORMAT2 = "yyyy-MM--dd HH-mm";
    public final static String FORMAT3 = "yyyy-MM-dd HH-mm-ss";

    public static String getNowDay() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT1);
        Date date = new Date();
        String time = sdf.format(date);
        return time;

    }

    public static String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT3);
        Date date = new Date();
        String _time = sdf.format(date);
        return _time;
    }

    public static String getThreeBeginDate(long time) {
        Long three = 3 * 24 * 60 * 60 * 1000l;
        Long threeDay = time - three;
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT1);
        Date threeDate = new Date(threeDay);
        String _time = sdf.format(threeDate);
        return _time;
    }

    public static String getMonthBeginDate(long time) {
        Long month = 30 * 24 * 60 * 60 * 1000l;
        Long monthDay = time - month;
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT1);
        Date monthDate = new Date(monthDay);
        String _time = sdf.format(monthDate);
        return _time;
    }

    public static String getThreeDayBeginTime(long time) {
        Long three = 3 * 24 * 60 * 60 * 1000l;
        Long threeDay = time - three;
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT3);
        Date threeDate = new Date(threeDay);
        String _time = sdf.format(threeDate);
        return _time;
    }

    public static String getTenBeginTime(long time) {
        Long ten = 10 * 24 * 60 * 60 * 1000l;
        Long tenDay = time - ten;
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT3);
        Date tenTime = new Date(tenDay);
        String _time = sdf.format(tenTime);
        return _time;
    }

    public static Date getDateSecond(long time) {
        Long month = 10 * 1000l;
        Long threeDay = time - month;
        Date threeTime = new Date(threeDay);
        return threeTime;
    }

    public static Date getThreeDaybeginTime(Date time) {
        Long three = 3 * 24 * 60 * 60 * 1000l;
        Long threeDay = time.getTime() - three;
        Date threeTime = new Date(threeDay);
        return threeTime;
    }

    public static String formatTimeNew(Date date) {
        if (date == null)
            return null;
        String checkTime = String.valueOf(date);
        if (checkTime != null && !"".equals(checkTime) && checkTime.length() > 19) {
            checkTime = checkTime.substring(0, 19);
        }
        return checkTime;
    }

    public static String formatTime(Date date) {
        if (date == null)
            return null;
        String checkTime = String.valueOf(date);
        if (checkTime != null && !"".equals(checkTime) && checkTime.length() > 19) {
            checkTime = checkTime.substring(0, 16);
        }
        return checkTime;
    }

    public static String formatDate(Date date) {
        if (date == null)
            return null;
        String checkTime = String.valueOf(date);
        if (checkTime != null && !"".equals(checkTime) && checkTime.length() > 19) {
            checkTime = checkTime.substring(0, 10);
        }
        return checkTime;
    }

    public static String getLastTime(String time, int lastTime) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT1);
        String newTime = null;
        try {
            Date date;
            newTime = time;
            date = sdf.parse(time);
            Long lastTimeDay = lastTime * 24 * 60 * 60 * 1000l;
            Long lastDay = date.getTime() + lastTimeDay;
            Date _date = new Date(lastDay);
            newTime = sdf.format(_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newTime;

    }


}
