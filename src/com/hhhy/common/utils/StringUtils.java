package com.hhhy.common.utils;

public class StringUtils{
    public static boolean notEmpty(String str){
        if(str==null || "".equals(str)) return false;
        return true;
    }
}