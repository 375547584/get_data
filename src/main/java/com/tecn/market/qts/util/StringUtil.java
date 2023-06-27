package com.tecn.market.qts.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    private static String string;
    public static char[] randomMetaData;

    public static boolean isBlank(String str) {
        return str == null || "".equals(str) || "null".equalsIgnoreCase(str);
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static String trim(String str) {
        return (str == null) ? "" : str.trim();
    }

    public static boolean isNum(String obj) {
        if (isBlank(obj)) {
            return false;
        }
        boolean flag = true;
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNumber = pattern.matcher(obj);
        if (!isNumber.matches()) {
            flag = false;
        }
        return flag;
    }

    public static boolean isDouble(String obj) {
        if (isBlank(obj)) {
            return false;
        }
        boolean flag = true;
        Pattern pattern = Pattern.compile(
                "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$");
        Matcher isNumber = pattern.matcher(obj);
        if (!isNumber.matches()) {
            flag = false;
        }
        return flag;
    }

    public static boolean isSex(String obj) {
        boolean flag = true;
        Pattern pattern = Pattern.compile("[男女]{1}");
        Matcher isRightSex = pattern.matcher(obj);
        if (!isRightSex.matches()) {
            flag = false;
        }
        return flag;
    }

    public static double convertPrecision(double value, int precision) {
        String strDoubleValue = String.valueOf(value);
        int len = strDoubleValue.indexOf(".");
        if (len != -1) {
            String integerValue = strDoubleValue.substring(0, len);
            String precisionValue = strDoubleValue.substring(len + 1);
            if (precisionValue.length() > precision) {
                precisionValue = precisionValue.substring(0, precision);
            }
            strDoubleValue = integerValue + "." + precisionValue;
        }
        return Double.parseDouble(strDoubleValue);
    }


    public static String listToStr(List<String> array, String reg) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.size(); ++i) {
            sb.append(array.get(i));
            if (i < array.size() - 1) {
                sb.append(reg);
            }
        }
        return sb.toString();
    }

    public static boolean isYYYYMMDD(String str) {
        if (trim(str).length() != 10) {
            return false;
        }
        String datePattern = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
        Pattern r = Pattern.compile(datePattern);
        Matcher m = r.matcher(str);
        return m.find();
    }

    public static Integer JudgeIsMoblie(HttpServletRequest request) {
        Integer user_agent = 0;
        String[] mobileAgents = {"iphone", "android", "phone", "mobile", "wap", "netfront", "java",
                "opera mobi", "opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony",
                "blackberry", "dopod", "nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp",
                "cldc", "motorola", "foma", "docomo", "up.browser", "up.link", "blazer", "helio", "hosin",
                "huawei", "novarra", "coolpad", "webos", "techfaith", "palmsource", "alcatel", "amoi",
                "ktouch", "nexian", "ericsson", "philips", "sagem", "wellcom", "bunjalloo", "maui",
                "smartphone", "iemobile", "spice", "bird", "zte-", "longcos", "pantech", "gionee",
                "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320", "240x320",
                "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
                "blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq",
                "java", "jigs", "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo",
                "midp", "mits", "mmef", "mobi", "mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper",
                "palm", "pana", "pant", "phil", "play", "port", "prox", "qwap", "sage", "sams", "sany",
                "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem", "smal", "smar", "sony",
                "sph-", "symb", "t-mo", "teli", "tim-", "tsm-", "upg1", "upsi", "vk-v", "voda", "wap-",
                "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-", "Googlebot-Mobile"};
        if (request.getHeader("User-Agent") != null) {
            String[] array = mobileAgents;
            int length = array.length;
            int i = 0;
            while (i < length) {
                String mobileAgent = array[i];
                if (request.getHeader("User-Agent").toLowerCase().indexOf(mobileAgent) >= 0) {
                    if (mobileAgent.equals("android")) {
                        user_agent = 2;
                        break;
                    }
                    if (mobileAgent.equals("iphone")) {
                        user_agent = 3;
                        break;
                    }
                    user_agent = 1;
                    break;
                } else {
                    ++i;
                }
            }
        }
        return user_agent;
    }

    public static String getRandomStr(int charLength, int numLength) {
        StringBuffer sb = new StringBuffer();
        int len = StringUtil.string.length();
        for (int i = 0; i < charLength; ++i) {
            sb.append(StringUtil.string.charAt((int) Math.round(Math.random() * (len - 1))));
        }
        for (int i = 0; i < numLength; ++i) {
            int anInt = new Random().nextInt(9);
            sb.append(anInt);
        }
        return sb.toString();
    }

    public static String randomStr(int num) {
        Random random = new Random();
        String tNonceStr = "";
        for (int i = 0; i < num; ++i) {
            tNonceStr += StringUtil.randomMetaData[random.nextInt(StringUtil.randomMetaData.length - 1)];
        }
        return tNonceStr;
    }


    static {
        StringUtil.string = "abcdefghijklmnopqrstuvwxyz";
        StringUtil.randomMetaData = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C',
                'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    }

    public static String replaceAction(String str) {
        if (StringUtils.isEmpty(str) || str.length() < 4) {
            return str;
        }

        String frontStr = str.substring(0, 3);
        Integer n = str.length() - 3;

        if (n <= 0) {
            return frontStr;
        }
        String newStr = frontStr + String.join("", Collections.nCopies(n, "*"));
        return newStr;
    }

    public static List<String> splitString(String str, String splitRegex) {
        if (StringUtil.isBlank(str) || StringUtil.isBlank(splitRegex)) {
            return new ArrayList<>();
        }

        List<String> strList = null;
        try {
            strList = Arrays.asList(str.split(splitRegex));
        } catch (Exception e) {

        }

        if (strList == null) {
            return new ArrayList<>();
        }

        for (String dstr : strList) {
            dstr.trim();
        }

        return strList;
    }


    public static Set<String> splitSetString(String str, String splitRegex) {
        if (StringUtil.isBlank(str) || StringUtil.isBlank(splitRegex)) {
            return new HashSet<>();
        }

        List<String> strList = null;
        try {
            strList = Arrays.asList(str.split(splitRegex));
        } catch (Exception e) {

        }

        if (strList == null) {
            return new HashSet<>();
        }

        for (String dstr : strList) {
            dstr.trim();
        }

        return new HashSet<>(strList);
    }

    public static Boolean convertBoolean(String s) {
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        return Boolean.parseBoolean(s);
    }

    public static Integer convertInteger(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        return Integer.valueOf(s);
    }

    public static int convertInt(String s) {
        if (StringUtils.isEmpty(s)) {
            return 0;
        }
        return Integer.parseInt(s);
    }

    public static void main(String[] args) {
        Integer id = StringUtil.convertInteger("291");
        System.out.println(id);
    }

    public static String toUTF8(byte[] bs) {
        try {
            return new String(bs, 0, bs.length, StandardCharsets.UTF_8).trim();
        }
        catch (Exception e) {
            System.out.println("toUTF8 异常");
        }
        return "";
    }

    public static String toUTF8(byte b) {
        try {
            byte[] bs = new byte[]{b};
            new String(bs, 0, bs.length, StandardCharsets.UTF_8).trim();
            return new String(bs, 0, bs.length, StandardCharsets.UTF_8).trim();
        }
        catch (Exception e) {
            System.out.println("toUTF8 异常");
        }
        return "";
    }

    public static BigDecimal convertBigDecimal(double a) {
        try {
            return BigDecimal.valueOf(a);
        }
        catch (Exception e) {
            System.out.println("convertBigDecimal 异常");
        }
        return BigDecimal.ZERO;
    }
}
