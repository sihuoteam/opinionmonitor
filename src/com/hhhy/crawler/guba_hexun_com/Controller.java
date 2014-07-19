package com.hhhy.crawler.guba_hexun_com;

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
    private final String BASE_URL = "http://news.search.hexun.com/cgi-bin/search/info_search.cgi?f=1&key=%B9%C9%C6%B1&s=1&pg=1&t=0&rel=";
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
        String html = GetHTML.getHtml("http://news.search.hexun.com/cgi-bin/search/info_search.cgi?f=1&key="+transKey+"&s=1&pg=1&t=0&rel=", "gb2312");

        html = html.replaceAll("&nbsp;","");
        Document document = Jsoup.parse(html);
        /*
        搜索关键词是否存在
         */
        Elements flag = document.select("div.list");
        if(flag.size()==0){
            //Todo ??
            System.out.println("nothing to found.....");
        }
        else{
            Elements tableEles =  document.select("div.list > ul").select("li");
            ArrayList<Element> tableList = new ArrayList<Element>();
            for(Element ele:tableEles){
                tableList.add(ele);
            }
            parsePages(tableList);
        }
    }
    public void parsePages(ArrayList<Element> tableList){
        for(Element ele:tableList){
            String title = ele.select("div.ul_t").select("h3").select("a").text();
            String time = FormatTime.getTime(ele.select("div.ul_t").select("h4").text(), "\\d+分钟前");
            if(FormatTime.isAfterToday(time)){
                if(!this.spyHistory.contains(title)){

                    String summary = ele.select("div.cont").text();
                    String url =ele.select("div.ul_t").select("h3").select("a").attr("href");
                    System.out.println("title:" + title);
                    System.out.println("url:" + url);
                    System.out.println("time:" + time);
                    System.out.println("summary:" + summary);
                    System.out.println("website:" + "和讯股吧");
                    System.out.println("----------------");
                    spyHistory.add(title);
                    //调接口~~~~~
                }
            }
        }
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        Controller controller = new Controller();
        controller.parseBoard("股票价格","");
       /* String html = GetHTML.getHtml("http://search.cs.com.cn/newsSimpleSearch.do?searchword=%E8%B4%B7%E6%AC%BE&time=2&contentType=Content&pn=1","UTF-8");
        Document document = Jsoup.parse(html);
        Elements tables = document.select("div:has(div.hei12)");
        for(Element ele:tables){
            System.out.println(ele);
        }*/
        // System.out.println(java.net.URLEncoder.encode("投资基金", "UTF-8"));
    }
}
