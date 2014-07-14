package com.hhhy.crawler.bbs_hexun_com;

import com.hhhy.crawler.util.FormatTime;
import com.hhhy.crawler.util.GetHTML;
import com.hhhy.crawler.util.MyLog;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Ghost
 * Date: 14-7-8
 * Time: 下午3:36
 * To change this template use File | Settings | File Templates.
 */
public class Controller {
    private final String BASE_URL = "http://bbs.hexun.com/search/?q=%B9%C9%C6%B1%BC%DB%B8%F1&type=1&Submit=";
    public final String keyWordsLocation = "./keyWords.txt";
    public ArrayList<String> spyHistory;
    public ArrayList<String> keyWordsList ;
    Controller(){
        this.spyHistory = new ArrayList<String>();//the history record got earlier in today.
        this.keyWordsList = new ArrayList<String>();
    }

    public void parseBoard(String keyWord,String BASE_URL){
        String transKey = "";
        try {
            transKey = URLEncoder.encode(keyWord, "gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String html = GetHTML.getHtml("http://bbs.hexun.com/search/?q=" + transKey + "&type=1&Submit=", "gb2312");

        html = html.replaceAll("&nbsp;","");
        Document document = Jsoup.parse(html);
        /*
        搜索关键词是否存在
         */
        Elements flag = document.select("tr.bg");
        if(flag.size()==0){
            //Todo ??
            System.out.println("nothing to found.....");
        }
        else{
            Elements tableEles =  document.select("tr.bg");
            ArrayList<Element> tableList = new ArrayList<Element>();
            for(Element ele:tableEles){
                tableList.add(ele);
            }
            parsePages(tableList);
        }
    }
    public void parsePages(ArrayList<Element> tableList){
        for(Element ele:tableList){
            String title = ele.select("td.f14").select("a").text();
            String time = "20"+ele.select("td").last().select("p").text()+":00";
            if(FormatTime.isAfterToday(time)){
                if(!this.spyHistory.contains(title)){

                    String summary = "";
                    String url = ele.select("td.f14").select("a").attr("href");
                    MyLog.logINFO("title:" + title);
                    MyLog.logINFO("url:" + url);
                    MyLog.logINFO("time:" + time);
                    MyLog.logINFO("summary:" + summary);
                    MyLog.logINFO("website:" + "和讯论坛");
                    MyLog.logINFO("----------------");
                    spyHistory.add(title);
                    //调接口~~~~~
                }
            }
        }
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        //parseBoard("股票价格","");
       /* String html = GetHTML.getHtml("http://search.cs.com.cn/newsSimpleSearch.do?searchword=%E8%B4%B7%E6%AC%BE&time=2&contentType=Content&pn=1","UTF-8");
        Document document = Jsoup.parse(html);
        Elements tables = document.select("div:has(div.hei12)");
        for(Element ele:tables){
            System.out.println(ele);
        }*/
        // System.out.println(java.net.URLEncoder.encode("投资基金", "UTF-8"));
    }
}
