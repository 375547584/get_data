package com.tecn.market.qts.util;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;


public class TimeUtils {
    /**
     * 判断 nowTime 是否在beginTime和 endTime之间
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean isSomeTimeInterval(LocalTime nowTime, LocalTime beginTime, LocalTime endTime) {
        if(nowTime == null || beginTime == null || endTime == null) {
            return false;
        }

        if(nowTime.isAfter(beginTime) && nowTime.isBefore(endTime)) {
            return true;
        }

        return false;
    }

    public static boolean is2HourBefore(Long timestamp) {
        if (System.currentTimeMillis() - timestamp > 60 * 60 * 2 * 1000) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param date 202210180925
     * @return 20221018
     */
    public static String getDate(String date) {
        if(StringUtils.isEmpty(date)) {
            return null;
        }
        if(date.length() < 8) {
            return null;
        }
        return date.substring(0, 8);
    }


    public static String getTime() {
        String date = LocalDateUtils.format(LocalDateTime.now(), LocalDateUtils.formatterDD4);
        if (Objects.equals(date.charAt(8), '0')) {
            return date.substring(9);
        }
        else {
            return date.substring(8);
        }
    }

    /**
     * @param date 202210180925
     * @return 925
     */
    public static String getTime(String date) {
        if(StringUtils.isEmpty(date)) {
            return null;
        }
        if(date.length() < 12) {
            return null;
        }
        if (Objects.equals(date.charAt(8), '0')) {
            return date.substring(9);
        }
        else {
            return date.substring(8);
        }
    }

    public static String getHMSTime(long time) {
        int hour = (int)time/(60*60*1000);
        int minute = (int)time%(60*60*1000)/(60*1000);
        int sec = (int)time%(60*60*1000)%(60*1000)/1000;
        int mils = (int)time%(60*60*1000)%(60*1000)%1000%1000;
        return String.format("%s小时%s分钟%s秒%s毫秒", hour, minute,sec,mils);
    }

    public static void main(String[] args) throws ParseException {
        System.out.println("2022-06-20 null".substring(5));
        System.out.println(getDate("202210180925"));
        System.out.println(getTime("202210181925"));
        System.out.println(getTime("202210180925"));
        System.out.println(getHMSTime(1165135405));
    }


}
