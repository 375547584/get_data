package com.tecn.market.qts.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;


public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static String YYYY = "yyyy";
    public static String YYYY_MM = "yyyy-MM";
    public static String YYYY_MM_DD = "yyyy-MM-dd";
    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm:ss";
    private static final String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};
    private static final String amBeginTime = "09:30:00";
    private static final String amFinishTime = "11:30:00";
    private static final String noonBeginTime = "13:00:00";
    private static final String noonFinishTime = "14:57:00";
    private static final String pmBeginTime = "14:57:00";
    private static final String pmFinishTime = "15:00:00";
    private static final String amIsNotCancelBeginTime = "09:20:00";
    private static final String amIsNotCancelFinishTime = "09:30:00";
    private static final String pmIsNotCancelBeginTime = "14:57:00";
    private static final String pmIsNotCancelFinishTime = "15:00:00";
    private static final String amStartTime = "09:25:00";
    private static final String finishTime = "15:05:00";
    private static final String amBiddingTime = "09:27:00";
    private static final String dfTraderingTime = "09:40:00";

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 增加 LocalDateTime ==> Date
     */
    public static Date toDate(LocalDateTime temporalAccessor) {
        ZonedDateTime zdt = temporalAccessor.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    /**
     * 增加 LocalDate ==> Date
     */
    public static Date toDate(LocalDate temporalAccessor) {
        LocalDateTime localDateTime = LocalDateTime.of(temporalAccessor, LocalTime.of(0, 0, 0));
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }


    public static String timeConversionYMDHMS(String date, String time) {
        if(StringUtils.isEmpty(time) || StringUtils.isEmpty(date)) {
            return "";
        }

        StringBuilder dateStringBuffer = new StringBuilder(date);
        if(date.length() == 6) {
            dateStringBuffer.insert(4, "-");
        }
        else if(date.length() == 8) {
            dateStringBuffer.insert(4, "-");
            dateStringBuffer.insert(7, "-");
        }

        StringBuilder timeStringBuffer = new StringBuilder(time);
        if(time.length() == 3 || time.length() == 5) {
            timeStringBuffer.insert(0, "0");
        }
        if(timeStringBuffer.length() == 4) {
            timeStringBuffer.insert(2, ":");
        }
        else if(timeStringBuffer.length() == 6) {
            timeStringBuffer.insert(2, ":");
            timeStringBuffer.insert(5, ":");
        }

        return dateStringBuffer + " " +timeStringBuffer;
    }

    public static String timeConversionYMD(String date) {
        if(StringUtils.isEmpty(date)) {
            return "";
        }

        if(date.contains("-") || date.contains(" ") || date.contains(":")) {
            return date;
        }

        StringBuilder dateStringBuffer = new StringBuilder(date);
        if(date.length() == 6) {
            dateStringBuffer.insert(4, "-");
        }
        else if(date.length() == 8) {
            dateStringBuffer.insert(4, "-");
            dateStringBuffer.insert(7, "-");
        }
        return dateStringBuffer.toString();
    }

    public static String timeConversionHMS(String time) {
        if(StringUtils.isEmpty(time)) {
            return "";
        }

        if(time.contains("-") || time.contains(" ") || time.contains(":")) {
            return time;
        }

        StringBuilder stringBuffer = new StringBuilder(time);
        if(time.length() == 3 || time.length() == 5) {
            stringBuffer.insert(0, "0");
        }

        if(stringBuffer.length() == 4) {
            stringBuffer.insert(2, ":");
        }
        else if(stringBuffer.length() == 6) {
            stringBuffer.insert(2, ":");
            stringBuffer.insert(5, ":");
        }
        return stringBuffer.toString();
    }

    public static String timeConversionHM(String time) {
        if(StringUtils.isEmpty(time)) {
            return "";
        }

        if(time.contains("-") || time.contains(" ") || time.contains(":")) {
            return time;
        }

        String timeStr = time;
        if(time.length() == 17) {
            timeStr = time.substring(9,14);
        }
        else if(time.length() == 14) {
            timeStr = time.substring(9,14);
        }
        else if(time.length() == 12) {
            timeStr = time.substring(9,12);
        }

        StringBuilder stringBuffer = new StringBuilder(timeStr);
        if(time.length() == 3 || time.length() == 5) {
            stringBuffer.insert(0, "0");
        }

        StringBuilder stringB = new StringBuilder(stringBuffer.subSequence(0, 4).toString());
        if(stringB.length() == 6) {
            stringBuffer.insert(2, ":");
            stringBuffer.insert(5, ":");
        }
        else if(stringB.length() == 4) {
            stringBuffer.insert(2, ":");
        }



        return stringBuffer.toString();
    }



    public static void main(String[] args) {
        String timestr = timeConversionHM("95504");
       System.out.println(timestr);
    }
}