package com.tecn.market.qts.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/****
 * 股票代码
 * 002开头的是中小板的
 * 000开头是深圳的
 * 300是创业板的
 * 600开头是上海交易所上市的股票
 */
@Slf4j
public class StockUtils {

    public static final String sh = "sh";
    public static final String sz = "sz";

    /**
     * code 转 Symbol
     * @param code
     * @return
     */
    public static String codeToSymbol(String code) {
        if(StringUtils.isEmpty(code)) {
            return null;
        }

        if (code.contains(".")) {
            return code;
        }

        if (isSH(code)) {
            return String.format("%s.%s",code , sh).toLowerCase();
        }
        else if (isSZ(code)) {
            return String.format("%s.%s",code , sz).toLowerCase();
        }
        return code;
    }

    /**
     * symbol 转 code
     * @param symbol
     * @return
     */
    public static String symbolToCode(String symbol) {
        if(StringUtils.isEmpty(symbol)) {
            return null;
        }
        if (Symbols.indexSymbols.contains(symbol)) {
            return symbol;
        }
        String s = symbol.toLowerCase();
        String[] sb = s.split("\\.");
        return sb[0];
    }

    /**
     * 获取block
     * @param symbol SH 或者 SZ
     * @return
     */
    public static String symbolToBlock(String symbol) {
        if(StringUtils.isEmpty(symbol)) {
            return symbol;
        }
        String s = symbol.toUpperCase();
        String[] sb = s.split("\\.");
        return sb[1];
    }

    /**
     * 判断是否深市
     * @param code
     * @return
     */
    public static boolean isSZ(String code) {
        if (StringUtils.isBlank(code)) {
            return false;
        }
        String c = code.trim();
        return (c.startsWith("3") || c.startsWith("0")) && !c.startsWith("399");
    }

    /**
     * 判断是否沪市
     * @param code
     * @return
     */
    public static boolean isSH(String code) {
        if (StringUtils.isBlank(code)) {
            return false;
        }
        String c = code.trim();
        return c.startsWith("6");
    }

    /**
     * 判断是否科创板
     * @param code
     * @return
     */
    public static boolean isKCB(String code) {
        if (StringUtils.isBlank(code)) {
            return false;
        }
        String c = code.trim();
        return c.startsWith("688");
    }

    /**
     * 判断是ST
     * @param name
     * @return
     */
    public static boolean isST(String name) {
        if (StringUtils.isBlank(name)) {
            return false;
        }
        String n = name.trim().toLowerCase();
        return n.contains("st");
    }

    /**
     * 判断是ST
     * @param name
     * @return
     */
    public static boolean isFirstNew(String code, String name) {
        if (StringUtils.isBlank(name)) {
            return false;
        }
        String n = name.trim().toLowerCase();
        if(isCYB(code)) {
            return n.startsWith("c");
        }
        else {
            return n.startsWith("n");
        }
    }

    /**
     * 判断是否创业板
     * @param code
     * @return
     */
    public static boolean isCYB(String code) {
        if (StringUtils.isBlank(code)) {
            return false;
        }
        String c = code.trim();
        return c.startsWith("300") || c.startsWith("301");
    }

    /**
     * 判断是否中小板
     * @param code
     * @return
     */
    public static boolean isZXB(String code) {
        if (StringUtils.isBlank(code)) {
            return false;
        }
        String c = code.trim();
        return c.startsWith("002");
    }

    /**
     * 股票涨跌价
     *
     * @param code
     * @param name
     * @param price
     * @return
     */
    public static Map<String, String> getRiseFailStopPrice(List<String> holidayList, String code, String name, BigDecimal price, String date) {
        Map<String, String> map = new HashMap<>();
        //涨停价
        String riseStopPrice;
        //跌停价
        String failStopPrice;
        //涨幅
        BigDecimal riseRate;
        //跌幅
        BigDecimal failRate;

        // 科创板
        if (isKCB(code) || isCYB(code)) {
            riseRate = new BigDecimal("1.2");
            failRate = new BigDecimal("0.8");
        } else {
            riseRate = new BigDecimal("1.1");
            failRate = new BigDecimal("0.9");
        }

        //新股涨跌停价
        if (!isKCB(code) && isFirstNew(code, name)) {
            riseRate = new BigDecimal("1.44");
            failRate = new BigDecimal("0.64");
        }

        //ST类
        if (isST(name)) {
            riseRate = new BigDecimal("1.05");
            failRate = new BigDecimal("0.95");
        }

        if (isKCB(code) && timeJudgment(holidayList, date)) {
            riseStopPrice = "--";
            failStopPrice = "--";
        } else {
            riseStopPrice = price.multiply(riseRate).toString();
            failStopPrice = isKCB(code) ? price.multiply(failRate).toString() : price.multiply(failRate).toString();
        }

        map.put("riseStopPrice", riseStopPrice);
        map.put("failStopPrice", failStopPrice);
        return map;
    }

    /**
     * @param holidayList
     * @param date 时间-yyyy-MM-dd
     * @return
     */
    private static Boolean timeJudgment(List<String> holidayList, String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        StringBuffer stringBuffer = new StringBuffer(date);
//        stringBuffer.insert(4, "-");
//        stringBuffer.insert(7, "-");
        Date releaseDate;
        Date beginDate = new Date();
        Date finishDate = new Date();

        try {
            releaseDate = sdf.parse(date);
            for (int i = 0; i < 4; ) {
                if (!holidayList.contains(sdf.format(releaseDate))) {
                    i++;
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(releaseDate);
                calendar.add(Calendar.DATE, 1);
                releaseDate = calendar.getTime();
                finishDate = releaseDate;
            }
            beginDate = sdf.parse(date);

            beginDate = timeProcessing(beginDate, 9);
            finishDate = timeProcessing(finishDate, 9);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return DateUtils.isEffectiveDate(new Date(), beginDate, finishDate);
    }

    private static Date timeProcessing(Date time, Integer num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.HOUR_OF_DAY, num);
        time = calendar.getTime();
        return time;
    }

    public static List<String> codeToSymbols(List<String> stockCodes) {
        List<String> symbols = new ArrayList<>();
        for (String code : stockCodes) {
            String symbol = StockUtils.codeToSymbol(code);
            if (StringUtils.isNotEmpty(symbol)) {
                symbols.add(symbol);
            }
        }
        return symbols;
    }

    public static String getBlockCode(String industry) {
        if(StringUtils.isEmpty(industry)) {
            return industry;
        }
        Integer index = industry.indexOf(".");
        if(index == -1) {
            return industry.toLowerCase();
        }
        else {
            return industry.substring(0, index).toLowerCase();
        }
    }

    public static String LowerCase(String symbol) {
        if(StringUtils.isEmpty(symbol)) {
            return symbol;
        }
        return symbol.toLowerCase();
    }


    public static void main(String[] args) {
        String ash = symbolToBlock("516515.ss");
        System.out.println(ash);
    }

    public static boolean isNew(String date) {
        if(StringUtils.isEmpty(date)) {
            return false;
        }
        LocalDate firstday = LocalDateUtils.string2LocalDate(date, LocalDateUtils.formatterDD3);
        // 一月以内次新股
        return LocalDateUtils.ltXMonths(firstday, 1);
    }

    public static boolean isSubNew(String date) {
        if(StringUtils.isEmpty(date)) {
            return false;
        }
        LocalDate firstday = LocalDateUtils.string2LocalDate(date, LocalDateUtils.formatterDD3);
        // 一月以内次新股
        return LocalDateUtils.ltXYears(firstday, 1);
    }
    public static boolean isNew(LocalDate date) {
        if(date == null) {
            return false;
        }
        // 一月以内次新股
        return LocalDateUtils.ltXMonths(date, 1);
    }

    public static boolean isSubNew(LocalDate date) {
        if(date == null) {
            return false;
        }
        // 一月以内次新股
        return LocalDateUtils.ltXYears(date, 1);
    }
}

