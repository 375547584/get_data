package com.tecn.market.qts.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

public class LocalDateUtils {
    public static final DateTimeFormatter formatterDD = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter formatterDD1 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter formatterDD2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter formatterDD3 = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter formatterDD4 = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    public static final DateTimeFormatter formatterDD5 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter formatterDD6 = DateTimeFormatter.ofPattern("HHmm");
    public static final DateTimeFormatter formatterDD7 = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter formatterDD8 = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss");
    public static final DateTimeFormatter formatterDD9 = DateTimeFormatter.ofPattern("M月d日");
    public static final DateTimeFormatter formatterDD10 = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
    public static final DateTimeFormatter formatterDD11 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    public static final DateTimeFormatter formatterDD12 = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    public static final DateTimeFormatter formatterDD13 = DateTimeFormatter.ofPattern("HHmmss");
    public static final DateTimeFormatter formatterDD15 = DateTimeFormatter.ofPattern("yyyyMMdd Hmmss");
    public static final DateTimeFormatter formatterDD16 = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final DateTimeFormatter formatterDD17 = DateTimeFormatter.ofPattern("yyyy");
    public static final DateTimeFormatter formatterDD18 = DateTimeFormatter.ofPattern("yyyyMM");
    public static final DateTimeFormatter formatterDD19 = DateTimeFormatter.ofPattern("yyyy-MM");
    public static final DateTimeFormatter formatterDD20 = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static final DateTimeFormatter formatterDD21 = DateTimeFormatter.ofPattern("MM-dd HH:mm:ss");
    public static final DateTimeFormatter formatterDD22 = DateTimeFormatter.ofPattern("MM-dd");
    public static LocalDateTime todayBegin() {
        return LocalDate.now().atTime(0, 0, 0);
    }

    public static LocalDateTime todayEnd() {
        return LocalDate.now().atTime(23, 59, 59);
    }

    public static LocalDateTime beginDate(String beginDate, DateTimeFormatter formatter) {
        if (StringUtils.isEmpty(beginDate)) {
            return LocalDate.now().atTime(0, 0, 0);
        }
        LocalDateTime beginLocalDateTime = null;
        try {
            LocalDate beginLocalDate = LocalDate.parse(beginDate, formatter);
            beginLocalDateTime = beginLocalDate.atTime(0, 0, 0);
        } catch (Exception ex) {
            System.out.println(beginDate + " 时间转化失败");
        }
        return beginLocalDateTime;
    }

    public static LocalDateTime endDate(String endDate, DateTimeFormatter formatter) {
        if (StringUtils.isEmpty(endDate)) {
            return LocalDate.now().atTime(23, 59, 59);
        }
        LocalDateTime endLocalDateTime = null;
        try {
            LocalDate endLocalDate = LocalDate.parse(endDate, formatter);
            endLocalDateTime = endLocalDate.atTime(23, 59, 59);
        } catch (Exception ex) {
            System.out.println(endDate + " 时间转化失败");
        }
        return endLocalDateTime;
    }


    public static LocalDateTime toLocalDateTime(String dateStr) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, formatterDD);
        return localDateTime;
    }

    public static LocalDateTime string2LocalDateTime(String dateStr, DateTimeFormatter fmt) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, fmt);
        return localDateTime;
    }


    public static LocalDate string2LocalDate(String dateStr, DateTimeFormatter fmt) {
        LocalDate localDate = LocalDate.parse(dateStr, fmt);
        return localDate;
    }


    public static LocalDateTime string2LocalDateTime(String dateStr) {
        TemporalAccessor temporalAccessor = DateTimeFormatter.ISO_DATE.parse(dateStr);
        LocalDateTime localDateTime = LocalDateTime.of(
                secureGet(temporalAccessor, ChronoField.YEAR),
                secureGet(temporalAccessor, ChronoField.MONTH_OF_YEAR),
                secureGet(temporalAccessor, ChronoField.DAY_OF_MONTH),
                secureGet(temporalAccessor, ChronoField.HOUR_OF_DAY),
                secureGet(temporalAccessor, ChronoField.MINUTE_OF_HOUR),
                secureGet(temporalAccessor, ChronoField.SECOND_OF_MINUTE),
                secureGet(temporalAccessor, ChronoField.NANO_OF_SECOND)
        );

        return localDateTime;
    }

    /**
     * 安全获取时间的某个属性
     *
     * @param temporalAccessor 需要获取的时间对象
     * @param chronoField      需要获取的属性
     * @return 时间的值，如果无法获取则默认为 0
     */
    private static int secureGet(TemporalAccessor temporalAccessor, ChronoField chronoField) {
        if (temporalAccessor.isSupported(chronoField)) {
            return temporalAccessor.get(chronoField);
        }
        return 0;
    }

    public static String format(LocalDate localDate) {
        if (localDate == null) {
            return "";
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(dateTimeFormatter);
    }

    public static String format(LocalDate localDate, DateTimeFormatter dateTimeFormatter) {
        if (localDate == null) {
            return "";
        }
        return localDate.format(dateTimeFormatter);
    }

    public static String format(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return "";
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String datestr = localDateTime.format(dateTimeFormatter);
        return datestr;
    }

    public static String format(LocalDateTime localDateTime, String pattern) {
        if (localDateTime == null || pattern == null) {
            return "";
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        String datestr = localDateTime.format(dateTimeFormatter);
        return datestr;
    }


    public static String format(LocalDateTime localDate, DateTimeFormatter dateTimeFormatter) {
        if (localDate == null || dateTimeFormatter == null) {
            return "";
        }
        return dateTimeFormatter.format(localDate);
    }

    public static LocalDateTime long2LocalDateTime(Long time) {
        // long转LocalDateTime
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
    }

    public static LocalDateTime long2LocalDateTime(int time) {
        // long转LocalDateTime
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time* 1000L), ZoneId.systemDefault());
    }

    public static String formatLocalDateTime(LocalDateTime localDate) {
        if (localDate == null) {
            return "";
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(dateTimeFormatter);
    }



    public static LocalDateTime string2LocalDateTimeV2(String str) {
        if(StringUtils.isEmpty(str)) {
            return null;
        }
        String dateStr = str;
        DateTimeFormatter fmt = null;
        if(str.length() == 17) {
            dateStr = str.substring(0,14);
            fmt = LocalDateUtils.formatterDD1;
        }
        else if(str.length() == 14) {
            fmt = LocalDateUtils.formatterDD1;
        }
        else if(str.length() == 12) {
            fmt = LocalDateUtils.formatterDD4;
        }
        else if(str.length() == 8) {
            fmt = LocalDateUtils.formatterDD3;
        }
        if (fmt != null) {
            return string2LocalDateTime(dateStr, fmt);
        }
        return null;
    }

    public static Long getMillis(LocalDateTime localDateTime) {
        if(localDateTime == null) {
            return 0L;
        }
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static LocalDateTime fromHMSsss(String str) {
        if(StringUtils.isEmpty(str)) {
            return null;
        }
        String dateStr = str;
        DateTimeFormatter fmt = null;
        if(str.length() == 9) {
            dateStr = str.substring(0,6);
            fmt = LocalDateUtils.formatterDD13;
        }
        else if(str.length() == 8) {
            dateStr = "0"+str.substring(0,5);
            fmt = LocalDateUtils.formatterDD13;
        }
        else if(str.length() == 6) {
            fmt = LocalDateUtils.formatterDD13;
        }
        else if(str.length() == 5) {
            dateStr = "0"+str;
            fmt = LocalDateUtils.formatterDD13;
        }
        else if(str.length() == 4) {
            fmt = LocalDateUtils.formatterDD6;
        }
        else if(str.length() == 3) {
            dateStr = "0"+str;
            fmt = LocalDateUtils.formatterDD6;
        }
        LocalTime localTime =  string2LocalTime(dateStr, fmt);
        if(localTime != null) {
            return LocalDateTime.of(LocalDate.now(), localTime);
        }
        return null;
    }

    public static LocalTime string2LocalTime(String dateStr, DateTimeFormatter fmt) {
        if (StringUtils.isEmpty(dateStr) || fmt == null) {
            return null;
        }

        if(dateStr.length() == 5) {
            dateStr = "0"+ dateStr;
        }

        return LocalTime.parse(dateStr, fmt);
    }

    public static LocalDateTime fromYMDHMSsss(String str) {
        if(StringUtils.isEmpty(str)) {
            return null;
        }
        String dateStr = str;
        DateTimeFormatter fmt = null;
        if(str.length() == 17) {
            dateStr = str.substring(0,14);
            fmt = LocalDateUtils.formatterDD1;
        }
        else if(str.length() == 14) {
            fmt = LocalDateUtils.formatterDD1;
        }
        else if(str.length() == 12) {
            fmt = LocalDateUtils.formatterDD4;
        }
        else if(str.length() == 8) {
            fmt = LocalDateUtils.formatterDD3;
        }
        if (fmt != null) {
            return string2LocalDateTime(dateStr, fmt);
        }
        return null;
    }


    public static long getBetweenYears(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            return 0;
        }
        // 是否大于等于x月
        Period period = Period.between(start, end);
        return period.getYears();
    }

    public static boolean geXYears(LocalDate localDate, int x) {
        if (localDate == null) {
            return false;
        }
        // 是否大于等于x年
        long years = getBetweenYears(localDate, LocalDate.now());
        if (years >= x) {
            return true;
        }
        return false;
    }

    public static boolean gtXYears(LocalDate localDate, int x) {
        if (localDate == null) {
            return false;
        }
        // 是否大于x年
        long years = getBetweenYears(localDate, LocalDate.now());
        if (years > x) {
            return true;
        }
        return false;
    }

    public static boolean leXYears(LocalDate localDate, int x) {
        if (localDate == null) {
            return false;
        }
        // 是否小于等于x年
        long years = getBetweenYears(localDate, LocalDate.now());
        if (years <= x) {
            return true;
        }
        return false;
    }

    public static boolean ltXYears(LocalDate localDate, int x) {
        if (localDate == null) {
            return false;
        }
        // 是否小于x年
        long years = getBetweenYears(localDate, LocalDate.now());
        if (years < x) {
            return true;
        }
        return false;
    }

    public static long getBetweenMonths(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            return 0;
        }
        // 是否大于等于x月
        Period period = Period.between(start, end);
        return period.toTotalMonths();
    }

    public static boolean geXMonths(LocalDate localDate, int x) {
        if (localDate == null) {
            return false;
        }
        // 是否大于等于x月
        long months = getBetweenMonths(localDate, LocalDate.now());
        if (months >= x) {
            return true;
        }
        return false;
    }

    public static boolean gtXMonths(LocalDate localDate, int x) {
        if (localDate == null) {
            return false;
        }
        // 是否大于x月
        long months = getBetweenMonths(localDate, LocalDate.now());
        if (months > x) {
            return true;
        }
        return false;
    }


    public static boolean leXMonths(LocalDate localDate, int x) {
        if (localDate == null) {
            return false;
        }
        // 是否小于等于x月
        long months = getBetweenMonths(localDate, LocalDate.now());
        if (months <= x) {
            return true;
        }
        return false;
    }

    public static boolean ltXMonths(LocalDate localDate, int x) {
        if (localDate == null) {
            return false;
        }
        // 是否小于x月
        long months = getBetweenMonths(localDate, LocalDate.now());
        if (months < x) {
            return true;
        }
        return false;
    }


    public static long getBetweenDays(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            return 0;
        }
        // 是否大于等于x月
        Period period = Period.between(start, end);
        return period.getYears() * 365 + period.getMonths() * 30 + period.getDays();
    }

    public static boolean geXDays(LocalDate localDate, int x) {
        if (localDate == null) {
            return false;
        }
        // 是否大于等于x月
        long days = getBetweenDays(localDate, LocalDate.now());
        if (days >= x) {
            return true;
        }
        return false;
    }

    public static boolean gtXDays(LocalDate localDate, int x) {
        if (localDate == null) {
            return false;
        }
        // 是否大于x天
        long days = getBetweenDays(localDate, LocalDate.now());
        if (days > x) {
            return true;
        }
        return false;
    }

    public static boolean leXDays(LocalDate localDate, int x) {
        if (localDate == null) {
            return false;
        }
        // 是否小于等于x月
        long days = getBetweenDays(localDate, LocalDate.now());
        if (days <= x) {
            return true;
        }
        return false;
    }

    public static boolean ltXDays(LocalDate localDate, int x) {
        if (localDate == null) {
            return false;
        }
        // 是否小于x天
        long days = getBetweenDays(localDate, LocalDate.now());
        if (days < x) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        LocalTime localDateTime = string2LocalTime("92310", formatterDD13);
        System.out.println(format(LocalDate.from(localDateTime)));

        System.out.println(format(LocalDate.now().atStartOfDay()));

        //LocalDateTime localDateTime = LocalDateUtils.string2LocalDateTime("20220914", LocalDateUtils.formatterDD3);
        System.out.println("2022-06-20 null".substring(5));
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("currentTimeMillis" + currentTimeMillis);

        System.out.println("currentTimeMillis" + format(long2LocalDateTime(currentTimeMillis)));

        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("milliSecond" + milliSecond);

        System.out.println("string2LocalDateTime" + string2LocalDateTime("20220910 232526"));

    }
}
