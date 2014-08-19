package com.hhhy.common.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 8/19/2014.
 */
public class ShowUtil {

    /*
    dimAna_dataSource & mediaSource
     */
    public static String dimAna_dataSourceRoundData(Map<String,String> sourceStatis){
//        String roundData = "['Firefox',   45.0],['IE',       26.8],['Chrome',   12.8],['Safari',    8.5],['Opera',     6.2],['Others',   0.7]";
        StringBuilder sb = new StringBuilder();
        for(String key:sourceStatis.keySet()){
            sb.append("['" + key + "'," + sourceStatis.get(key) + "],");
        }
        return sb.toString().substring(0,sb.toString().length()-1);
    }

    public static String dimAna_dataSourceZhuSource(Map<String,String> sourceStatis){
//        String zhuSource = "'Africa', 'America', 'Asia', 'Europe', 'Oceania'";
        StringBuilder sb = new StringBuilder();
        for(String key:sourceStatis.keySet()) {
            sb.append("'" + key + "',");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    public static String dimAna_dataSourceZhuData(Map<String,String> sourceStatis){
//        String zhuData="107, 31, 635, 203, 2";
        StringBuilder sb = new StringBuilder();
        for(String key:sourceStatis.keySet()) {
            sb.append(sourceStatis.get(key) + ",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /*
    dimAna_emotionTrend  & dimAna_sentiTrend
     */
    public static String dimAna_trendDates(List<String> dates){
    /* String dates = "'2014-8-1','2014-8-2','2014-8-3','2014-8-4','2014-8-5'"; */
        StringBuilder sb = new StringBuilder();
        for(String date:dates){
            sb.append("'" + date + "',");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }
    public static String dimAna_trendPostTrend(List<String> dates){
    /* String postEmotionTrend = "1,4,3,2,5";*/
        StringBuilder sb = new StringBuilder();
        for(String date:dates){
            sb.append(date + ",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    public static void main(String[] args) {
//        String a = "aaa";
//        System.out.println(a.substring(0,a.length()-1));
        Map<String,String> test = new HashMap<String, String>();
        test.put("aaa","12");
        test.put("bbb","14");
        test.put("ccc","17");
        test.put("ddd","19");
        List<String> test1 = new LinkedList<String>();
        test1.add("111");
        test1.add("222");
        test1.add("e33");

    }
}