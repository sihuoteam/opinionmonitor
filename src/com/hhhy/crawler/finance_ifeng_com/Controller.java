package com.hhhy.crawler.finance_ifeng_com;

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
 * Date: 14-6-6
 * Time: 下午3:12
 * To change this template use File | Settings | File Templates.
 */
public class Controller {
    public final String BASE_URL = "http://search.ifeng.com/sofeng/search.action?";
    public final String keyWordsLocation = "./keyWords.txt";
    public ArrayList<String> spyHistory;//the history record got earlier in today.

    public ArrayList<String> keyWordsList;
    Controller(){
        this.spyHistory = new ArrayList<String>();
        this.keyWordsList = new ArrayList<String>();
    }

    public void parseBoard(String keyWord,String BASE_URL){
        String transKey = "";
        try {
            transKey = URLEncoder.encode(keyWord, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String html = GetHTML.getHtml("http://search.ifeng.com/sofeng/search.action?q="+transKey+"&c=1","UTF-8");
        html = html.replaceAll("&nbsp;","");
        Document document = Jsoup.parse(html);

        String flag = document.select("div.mainContent").select("div.mainM").select("h1").text();
        if(flag.equals("找不到和您的")){
            //Todo ??
            System.out.println("没找到");
        }
        else{
            Elements tableEles = document.select("div.mainContent").select("div.mainM").select("div.searchResults");
            ArrayList<Element> tableList = new ArrayList<Element>();
            for(Element ele:tableEles){
                tableList.add(ele);
            }
            parsePages(tableList);
        }
    }
    public void parsePages(ArrayList< Element > tableList){
        for(Element ele:tableList){
            String title = ele.select("p").first().text();
            String orgTime = ele.select("p").get(2).text();
            String time = tool.getTimeStr(orgTime);
            if(FormatTime.isAfterToday(time)){
                if(!spyHistory.contains(title)){
                    String summary = ele.select("p").get(1).text();
                    String url = ele.select("p").first().select("a").attr("href");
                    System.out.println("title:"+title);
                    System.out.println("url:"+url);
                    System.out.println("time:"+time);
                    System.out.println("summary:"+summary);
                    System.out.println("website:"+"凤凰财经");
                    System.out.println("----------------");
                    spyHistory.add(title);
                    //调接口~~~~~
                }
            }
        }
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        Controller controller = new Controller();
        controller.parseBoard("财经新闻","");
       /* String html = GetHTML.getHtml("http://search.cs.com.cn/newsSimpleSearch.do?searchword=%E8%B4%B7%E6%AC%BE&time=2&contentType=Content&pn=1","UTF-8");
        Document document = Jsoup.parse(html);
        Elements tables = document.select("div:has(div.hei12)");
        for(Element ele:tables){
            System.out.println(ele);
        }*/
        // System.out.println(java.net.URLEncoder.encode("投资基金", "UTF-8"));
    }
}

