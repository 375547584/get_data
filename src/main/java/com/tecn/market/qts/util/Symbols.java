package com.tecn.market.qts.util;

import java.util.Arrays;
import java.util.List;

/**
 * 股票常量信息
 *
 * @author capital
 */
public class Symbols {
    /**
     * 所有沪深A股
     */
    public static String CNASymbol = "CNA.ci";

    /**
     * 所有沪深A股
     */
    public static String CNACode = "CNA";

    /**
     * 上证指数
     */
    public static String sh_index = "000001.sh";
    /**
     * 深证指数
     */
    public static String sz_index = "399001.sz";
    /**
     * 创业板指数
     */
    public static String cy_index = "399006.sz";

    /**
     * 上证、深证、创业三大指数代码
     */
    public static List<String> indexSymbols = Arrays.asList("000001.sh", "399001.sz", "399006.sz");

    /**
     * 上证、深证、创业三大指数代码
     */
    public static List<String> indexCodes = Arrays.asList("000001", "399001", "399006");
}
