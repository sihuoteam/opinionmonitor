package com.hhhy.crawler.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Ghost
 * Date: 14-6-4
 * Time: 下午3:14
 * To change this template use File | Settings | File Templates.
 */
public class FormatTime {
    public static Date getDate(String time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
           Date date = simpleDateFormat.parse(time);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
    public static String getCurrentFormatTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }
    public static String getFormatTimeAfterXDays(int xDay){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE,day+xDay);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }
    public static String getFormatTimeBeforeXDays(int xDay){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE,day-xDay);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }

    public static boolean isAfterToday(String time){
        String today = getCurrentFormatTime().split(" ")[0]+" 00:00:00";
        Date Today = getDate(today);
        Date Time = getDate(time);

        if(Time.before(Today))
            return false;
        else
            return true;
    }

    public static void main(String[] args){
        System.out.println(getFormatTimeAfterXDays(1));
    }
}
