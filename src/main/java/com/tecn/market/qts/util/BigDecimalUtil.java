package com.tecn.market.qts.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;

public class BigDecimalUtil {
    public static BigDecimal multiply(Long a, BigDecimal b) {
        if(a == null || b == null) {
            return null;
        }
        return BigDecimal.valueOf(a).multiply(b);
    }

    public static BigDecimal multiply(BigDecimal a, Integer b) {
        if(a == null || b == null) {
            return null;
        }
        return BigDecimal.valueOf(b).multiply(a);
    }

    public static BigDecimal divide4Scale(BigDecimal a, BigDecimal b) {
        if(a == null || b == null) {
            return null;
        }

        if(BigDecimalUtil.eqZero(b)) {
            return BigDecimal.ZERO;
        }
        return a.divide(b, 4, RoundingMode.HALF_UP);
    }

    public static BigDecimal divide100(Long a) {
        if (a == null) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(a).divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);
    }

    /**
     * a 减去 b
     *
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal minus(BigDecimal a, BigDecimal b) {
        if (a == null || b == null) {
            return BigDecimal.ZERO;
        }
        return a.subtract(b);
    }

    /**
     * 等于0
     *
     * @param a
     * @return
     */
    public static boolean eqZero(BigDecimal a) {
        if (a == null) {
            return true;
        }
        return a.compareTo(BigDecimal.ZERO) == 0;
    }

    public static BigDecimal multiply100(BigDecimal a) {
        if(a == null) {
            return null;
        }
        return multiply(a, 100);
    }

    public static void main(String[] args) throws ParseException {
        /*
         * BigDecimal floatingProfitAndLossRate =
         * BigDecimalUtil.divide4Scale(BigDecimalUtil.minus(100, 150), 150);
         * //String floatingProfitAndLossRateStr =
         * BigDecimalUtil.percentage(BigDecimalUtil.multiply2Scale(
         * floatingProfitAndLossRate, 100));
         * //System.out.println(floatingProfitAndLossRateStr);
         * String floatingProfitAndLossRateStr1 =
         * BigDecimalUtil.toPercent(floatingProfitAndLossRate);
         * System.out.println(floatingProfitAndLossRateStr1);
         * BigDecimal a = BigDecimal.valueOf(0.3303);
         * NumberFormat percent = NumberFormat.getPercentInstance();
         * percent.setMaximumFractionDigits(2);
         * String rate = percent.format(a.doubleValue());
         */
        // System.out.println(divide100Scale2(new BigDecimal(50)));
        /*
         * System.out.println(toPercent(new BigDecimal(0.3500)));
         * Integer registrationPositionNumber = 1000;
         * Integer bonus = 4;
         * Integer giftNumber = registrationPositionNumber * bonus / 10 ;
         */
        //System.out.println(toPercent100(BigDecimal.valueOf(45)));
        // System.out.println(BigDecimalUtil.plus(new BigDecimal(100), new
        // BigDecimal(50)).setScale(2, RoundingMode.HALF_UP));
        //System.out.println(BigDecimalUtil.eq(new BigDecimal(3), new BigDecimal(3)));

        Double num2 = (Double) NumberFormat.getPercentInstance().parse("30.86%");
        BigDecimal scale = new BigDecimal(num2).setScale(2, RoundingMode.HALF_UP);
        System.out.println(num2);
        System.out.println(scale);
    }
}
