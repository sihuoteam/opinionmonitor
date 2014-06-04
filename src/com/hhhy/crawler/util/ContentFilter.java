package com.hhhy.crawler.util;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Ghost
 * Date: 14-6-4
 * Time: 下午3:18
 * To change this template use File | Settings | File Templates.
 */
public class ContentFilter {
    public static boolean redundant(ArrayList<String> spyHistory,String title){
        if(spyHistory.contains(title))
            return true;
        else
            return false;
    }
}
