package com.hhhy.crawler.www_stcn_com;

/**
 * Created with IntelliJ IDEA.
 * User: Ghost
 * Date: 14-6-4
 * Time: 下午3:57
 * To change this template use File | Settings | File Templates.
 */


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
import java.util.ArrayList;

public class Controller {
    public final String BASE_URL = "http://app.stcn.com/?app=search&controller=index&action=search&type=article&wd=%E8%AF%81%E5%88%B8&advanced=1&catid=1&order=time&before=2014-06-04&after=2014-06-05";
    public final String keyWordsLocation = "./keyWords.txt";
    public ArrayList<String> spyHistory;//the history record got earlier in today.
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
        String before =  FormatTime.getCurrentFormatTime().split(" ")[0];
        String after = FormatTime.getFormatTimeAfterXDays(1).split(" ")[0];

        String html = GetHTML.getHtml("http://app.stcn.com/?app=search&controller=index&action=search&type=article&wd="+transKey+"&advanced=1&catid=1&order=time&before="+before+"&after="+after, "UTF-8");

        html = html.replaceAll("&nbsp;","");
        Document document = Jsoup.parse(html);


        String flag = document.select("form#form1").attr("action");
        if(flag.equals("SearchNotFound.aspx")){
            //Todo ??
        }
        else{
            Elements tableEles = document.select("div#search_list").select("dl");
            ArrayList<Element> tableList = new ArrayList<Element>();
            for(Element ele:tableEles){
                tableList.add(ele);
            }
            parsePages(tableList);
        }
    }
    public void parsePages(ArrayList< Element > tableList){
        for(Element ele:tableList){
            String title = ele.select("dt").select("a").text();
            if(!this.spyHistory.contains(title)){
                String time = ele.select("dd").last().select("span").first().text()+":00";
                String summary = ele.select("dd.info").text();
                String url = ele.select("dt").select("a").attr("href");
                System.out.println("title:"+title);
                System.out.println("url:"+url);
                System.out.println("time:"+time);
                System.out.println("summary:"+summary);
                System.out.println("website:"+"证券时报");
                System.out.println("----------------");
                spyHistory.add(title);
                //调接口~~~~~
            }
        }
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
       /* String html = GetHTML.getHtml("http://search.cs.com.cn/newsSimpleSearch.do?searchword=%E8%B4%B7%E6%AC%BE&time=2&contentType=Content&pn=1","UTF-8");
        Document document = Jsoup.parse(html);
        Elements tables = document.select("div:has(div.hei12)");
        for(Element ele:tables){
            System.out.println(ele);
        }*/
        // System.out.println(java.net.URLEncoder.encode("投资基金", "UTF-8"));
    }
}

