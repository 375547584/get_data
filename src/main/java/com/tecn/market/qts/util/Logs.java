package com.tecn.market.qts.util;

import java.util.Arrays;
import java.util.List;

public class Logs {

    public static void printList(List<String> symbols) {
        System.out.println(Arrays.toString(symbols.toArray()));
    }

    public static void print(String s) {
        System.out.println(s);
    }
}

