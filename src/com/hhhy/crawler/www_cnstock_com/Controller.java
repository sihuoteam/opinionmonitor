package com.hhhy.crawler.www_cnstock_com;

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
    public final String BASE_URL = "http://search.cnstock.com/Search.aspx";
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

        String html = GetHTML.getHtml("http://search.cnstock.com/search/result?k="+transKey,"UTF-8");    //  http://search.cnstock.com/search/result?k=%E8%82%A1%E7%A5%A8

        html = html.replaceAll("&nbsp;","");
        Document document = Jsoup.parse(html);

        Elements flag = document.select("div.result-article");
        if(flag.size()==0){
            //Todo ??
            System.out.println("nothing to show");
        }
        else{
            Elements tableEles = document.select("div.result-article");
            ArrayList<Element> tableList = new ArrayList<Element>();
            for(Element ele:tableEles){
                tableList.add(ele);
            }
            System.out.println(tableEles.size());
            parsePages(tableList);
        }
    }
    public void parsePages(ArrayList< Element > tableList){
        for(Element ele:tableList){
            String title = ele.select("h3.t").text();
            String time = FormatTime.getTime(ele.select("p.link").select("span").text(),"\\d+-\\d+-\\d+- \\d+:\\d+");
            if(FormatTime.isAfterToday(time)){
                if(!spyHistory.contains(title)){
                    String summary = ele.select("p.des").text();
                    String url = ele.select("h3.t").select("a").attr("href");
                    System.out.println("title:"+title);
                    System.out.println("url:"+url);
                    System.out.println("time:"+time);
                    System.out.println("summary:"+summary);
                    System.out.println("website:"+"上海证券");
                    System.out.println("----------------");
                    spyHistory.add(title);
                    //调接口~~~~~
                }
            }
        }
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        Controller controller = new Controller();
        controller.parseBoard("投资","");
       /* String html = GetHTML.getHtml("http://search.cs.com.cn/newsSimpleSearch.do?searchword=%E8%B4%B7%E6%AC%BE&time=2&contentType=Content&pn=1","UTF-8");
        Document document = Jsoup.parse(html);
        Elements tables = document.select("div:has(div.hei12)");
        for(Element ele:tables){
            System.out.println(ele);
        }*/
        // System.out.println(java.net.URLEncoder.encode("投资基金", "UTF-8"));
    }
}

