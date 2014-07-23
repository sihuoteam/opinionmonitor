package com.hhhy.crawler.www_askci_com;

import com.hhhy.crawler.util.FormatTime;
import com.hhhy.crawler.util.GetHTML;
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
 * Date: 14-7-6
 * Time: 下午3:47
 * To change this template use File | Settings | File Templates.
 */
public class Controller {
    public final String BASE_URL = "http://www.askci.com/";
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
            transKey = URLEncoder.encode(keyWord, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String html = GetHTML.getHtml("http://www.askci.com/search/1/zixun/" + transKey+"/", "utf-8");

        html = html.replaceAll("&nbsp;","");
        Document document = Jsoup.parse(html);
        /*
        搜索关键词是否存在
         */
        Elements flag = document.select("div.y_header").select("div.so_sj2").select("div.list_css_1").select("ul").select("li");
        if(flag.size()==0){
            //Todo ??
            System.out.println("nothing to found.....");
        }
        else{
            Elements tableEles = document.select("div.y_header").select("div.so_sj2").select("div.list_css_1").select("ul").select("li");
            ArrayList<Element> tableList = new ArrayList<Element>();
            for(Element ele:tableEles){
                tableList.add(ele);
            }
            parsePages(tableList);
        }
    }
    public void parsePages(ArrayList<Element> tableList){
        for(Element ele:tableList){
            String title = ele.select("p.list_sous").select("span.list_tit").select("a").text();
            String time = Subutils.getTime(ele.select("p.list_sous").select("span.day_hui").text());
            if(FormatTime.isAfterToday(time))
            if(!this.spyHistory.contains(title)){
                String summary = ele.select("p.main_tit").text();
                String url = ele.select("p.list_sous").select("span.list_tit").select("a").attr("href");
                System.out.println("title:"+title);
                System.out.println("url:"+url);
                System.out.println("time:"+time);
                System.out.println("summary:"+summary);
                System.out.println("website:"+"中商情报网");
                System.out.println("----------------");
                spyHistory.add(title);
                //调接口~~~~~
            }
        }
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        Controller controller = new Controller();
        controller.parseBoard("股票","");
       /* String html = GetHTML.getHtml("http://search.cs.com.cn/newsSimpleSearch.do?searchword=%E8%B4%B7%E6%AC%BE&time=2&contentType=Content&pn=1","UTF-8");
        Document document = Jsoup.parse(html);
        Elements tables = document.select("div:has(div.hei12)");
        for(Element ele:tables){
            System.out.println(ele);
        }*/
        // System.out.println(java.net.URLEncoder.encode("投资基金", "UTF-8"));
    }
}
