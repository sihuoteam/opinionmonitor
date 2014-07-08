package com.hhhy.crawler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: Ghost
 * Date: 14-5-30
 * Time: 上午9:29
 * To change this template use File | Settings | File Templates.
 */
public class test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String transKey = URLEncoder.encode("周鸿祎","utf-8");
        System.out.println(transKey);
    }
}
