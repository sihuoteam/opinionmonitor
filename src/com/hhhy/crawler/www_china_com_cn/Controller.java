package com.hhhy.crawler.www_china_com_cn;


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
 * Date: 14-7-4
 * Time: 上午10:54
 * To change this template use File | Settings | File Templates.
 */
public class Controller {
    public final String BASE_URL = "http://www.baidu.com/baidu";
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

        String html = GetHTML.getHtml("http://www.baidu.com/baidu?tn=baidu&word=" + transKey, "UTF-8");

        html = html.replaceAll("&nbsp;","");
        Document document = Jsoup.parse(html);



        Elements flagEles = document.select("div#wrapper").select("div#wrapper_wrapper").select("div#container").select("div#content_left").select("div[id]");

        if(flagEles.size()==0){
            //Todo ??
            System.out.println("nothing have found...");
        }
        else{
            Elements tableEles = document.select("div#wrapper").select("div#wrapper_wrapper").select("div#container").select("div#content_left").select("div[id]");
            ArrayList<Element> tableList = new ArrayList<Element>();
            for(Element ele:tableEles){
                tableList.add(ele);
            }
            parsePages(tableList);
        }
    }
    public void parsePages(ArrayList< Element > tableList){
        for(Element ele:tableList){
            String title = ele.select("h3.t").select("a").text();
            if(!this.spyHistory.contains(title)){
                String time = FormatTime.getCurrentFormatTime();
                String summary = ele.select("div.c-abstract").text();
                String url = ele.select("h3.t").select("a").attr("href");
                System.out.println("title:"+title);
                System.out.println("url:"+url);
                System.out.println("time:"+time);
                System.out.println("summary:"+summary);
                System.out.println("website:"+"中国网");
                System.out.println("----------------");
                spyHistory.add(title);
                //调接口~~~~~
            }
        }
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
      //  parseBoard("习近平","");
       /* String html = GetHTML.getHtml("http://search.cs.com.cn/newsSimpleSearch.do?searchword=%E8%B4%B7%E6%AC%BE&time=2&contentType=Content&pn=1","UTF-8");
        Document document = Jsoup.parse(html);
        Elements tables = document.select("div:has(div.hei12)");
        for(Element ele:tables){
            System.out.println(ele);
        }*/
        // System.out.println(java.net.URLEncoder.encode("投资基金", "UTF-8"));
    }
}
