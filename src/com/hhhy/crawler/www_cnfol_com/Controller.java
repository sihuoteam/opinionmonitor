package com.hhhy.crawler.www_cnfol_com;


import com.hhhy.crawler.util.FormatTime;
import com.hhhy.crawler.util.GetHTML;
import com.hhhy.crawler.util.GetSubString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ghost
 * Date: 14-6-3
 * Time: 下午5:12
 * To change this template use File | Settings | File Templates.
 */
public class Controller {
    public final String BASE_URL = "http://so.cnfol.com/cse/search";
    public final String keyWordsLocation = "./keyWords.txt";
    public ArrayList<String> spyHistory ;//the history record got earlier in today.
    public ArrayList<String> keyWordsList;
    Controller(){
        spyHistory = new ArrayList<String>();
        keyWordsList = new ArrayList<String>();

    }

    public void parseBoard(String keyWord,String BASE_URL){
        String transKey = "";
        try {
            transKey = URLEncoder.encode(keyWord, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String html = GetHTML.getHtml("http://so.cnfol.com/cse/search?q="+transKey+"&s=12596448179979580087","UTF-8");

        html = html.replaceAll("&nbsp;","");
        Document document = Jsoup.parse(html);

        /*
        搜索关键词是否存在
         */
        String flag = document.select("div#wrap").select("div#container").select("div#center").select("div#results").text();
        if(flag.equals("抱歉，没有找到")){
            //Todo ??
        }
        else{
            Elements tableEles = document.select("div#wrap").select("div#container").select("div#center").select("div#results").select("div.result");

            ArrayList<Element> tableList = new ArrayList<Element>();
            for(Element ele:tableEles){
                tableList.add(ele);
            }
            parsePages(tableList);
        }
    }
    public void parsePages(ArrayList< Element > tableList){
        for(Element ele:tableList){
            String title = ele.select("h3.c-title").select("a").text();
            String time = FormatTime.getTime(ele.select("span.c-showurl").text(),"\\d+-\\d+-\\d+");
            if(FormatTime.isAfterToday(time)){
                if(!this.spyHistory.contains(title)){
                    String summary = ele.select("div").select("div.c-content").select("div.c-abstract").text();
                    String url = ele.select("h3.c-title").select("a").attr("href");
                    System.out.println("title:"+title);
                    System.out.println("url:"+url);
                    System.out.println("time:"+time);
                    System.out.println("summary:"+summary);
                    System.out.println("website:"+"中金在线");
                    System.out.println("----------------");
                    spyHistory.add(title);
                    //调接口~~~~~
                }
            }
        }
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        Controller controller = new Controller();
        controller.parseBoard("基金","");
       /* String html = GetHTML.getHtml("http://search.cs.com.cn/newsSimpleSearch.do?searchword=%E8%B4%B7%E6%AC%BE&time=2&contentType=Content&pn=1","UTF-8");
        Document document = Jsoup.parse(html);
        Elements tables = document.select("div:has(div.hei12)");
        for(Element ele:tables){
            System.out.println(ele);
        }*/
        // System.out.println(java.net.URLEncoder.encode("投资基金", "UTF-8"));
    }
}

