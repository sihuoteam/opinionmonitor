package com.hhhy.crawler.cs_com_cn;

import com.hhhy.crawler.util.FormatTime;
import com.hhhy.crawler.util.GetHTML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Ghost
 * Date: 14-5-30
 * Time: 上午9:29
 * To change this template use File | Settings | File Templates.
 */
public class Controller {
    public final String BASE_URL = "http://search.cs.com.cn/newsSimpleSearch.do?";
    public final String keyWordsLocation = "./keyWords.txt";
    public ArrayList<String> spyHistory;
    public ArrayList<String> keyWordsList ;

    Controller(){
        this.spyHistory = new ArrayList<String>();
        this.keyWordsList = new ArrayList<String>();
    }

    public void parseBoard(String keyWord,String BASE_URL){
        String transKey = "";
        try {
            transKey = URLEncoder.encode(keyWord,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = "http://search.cs.com.cn/newsSimpleSearch.do?searchword="+transKey+"&time=2&contentType=Content";
        String html = GetHTML.getHtml(url, "UTF-8");
        String html1 = html.replaceAll("&nbsp;","");


        Document document = Jsoup.parse(html1);
        Element tableEles = document.select("table").last();
        String flag = tableEles.text();
        if(flag.contains("[没有检索到任何结果]")){
            System.out.println("nothing to show...");
        }
        else{
            ArrayList<Element> tableList = new ArrayList<Element>();

            Elements tables = document.select("div:has(div.hei12)");
            for(Element ele:tables){
                tableList.add(ele);
            }
            parsePages(tableList);
        }
    }
    public void parsePages(ArrayList< Element > tableList){
        for(Element ele:tableList){
            String time = ele.select("tbody").select("td").last().text().replace("  ","");
            String title = ele.select("tbody").select("td").first().select("a").text();
            try {
                Date date = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse(time);
                if(FormatTime.isAfterToday(date)){
                    if(!spyHistory.contains(title)){
                        String url = ele.select("tbody").select("td").first().select("a").attr("href");
                        String summary = ele.select("div.hei12").text();
                        String website = "中国证券网";

                        System.out.println("title:"+title);
                        System.out.println("url:"+url);
                        System.out.println("time:"+time);
                        System.out.println("summary:"+summary);
                        System.out.println("website:"+website);
                        System.out.println("----------------");
                        //调接口~~~~~
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        Controller con = new Controller();
        con.parseBoard("投资", "");
       /* String html = GetHTML.getHtml("http://search.cs.com.cn/newsSimpleSearch.do?searchword=%E8%B4%B7%E6%AC%BE&time=2&contentType=Content&pn=1","UTF-8");
        Document document = Jsoup.parse(html);
        Elements tables = document.select("div:has(div.hei12)");
        for(Element ele:tables){
            System.out.println(ele);
        }*/
       // System.out.println(java.net.URLEncoder.encode("投资基金", "UTF-8"));
    }
}
