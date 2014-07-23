package com.hhhy.crawler.www_ce_cn;


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
 * Date: 14-7-3
 * Time: 下午5:37
 * To change this template use File | Settings | File Templates.
 */
public class Controller {
    public final String BASE_URL = "http://www.so.com/s";
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

        String html = GetHTML.getHtml("http://www.so.com/s?q="+transKey+"&ie=gbk&src=zz_www_ce_cn&site=ce.cn&rg=1", "utf-8");

        html = html.replaceAll("&nbsp;","");
        Document document = Jsoup.parse(html);

        String flag = document.select("div#main").select("ul#m-result").select("li.res-list").text();
        if(flag.equals("")){
            //Todo ??
            System.out.println("nothing have found...");
        }
        else{
            Elements tableEles = document.select("div#main").select("ul#m-result").select("li.res-list");
            ArrayList<Element> tableList = new ArrayList<Element>();
            for(Element ele:tableEles){
                tableList.add(ele);
            }
            parsePages(tableList);
        }
    }
    public void parsePages(ArrayList< Element > tableList){
        for(Element ele:tableList){
            String title = ele.select("h3.res-title").select("a").text();
<<<<<<< HEAD
            if(!this.spyHistory.contains(title)){
                String time = FormatTime.getCurrentFormatTime();
                String summary = ele.select("h3.res-title + div").text();
                String url = ele.select("h3.res-title").select("a").attr("href");
                System.out.println("title:"+title);
                System.out.println("url:"+url);
                System.out.println("time:"+time);
                System.out.println("summary:"+summary);
                System.out.println("website:"+"中国经济网");
                System.out.println("----------------");
                spyHistory.add(title);
                //调接口~~~~~
=======
            String time = FormatTime.getTime(ele.select("p.res-linkinfo").select("cite").text(),"\\d+-\\d+-\\d+");
            if(FormatTime.isAfterToday(time)){
                if(!this.spyHistory.contains(title)){
                    String summary = ele.select("h3.res-title + div").text();
                    String url = ele.select("h3.res-title").select("a").attr("href");
                    System.out.println("title:"+title);
                    System.out.println("url:"+url);
                    System.out.println("time:"+time);
                    System.out.println("summary:"+summary);
                    System.out.println("website:"+"中国经济网");
                    System.out.println("----------------");
                    spyHistory.add(title);
                    //调接口~~~~~
                }
>>>>>>> 2a70bb43dff7ff3bf2a1edbc50cee2db2f1ba9a1
            }
        }
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
       Controller controller = new Controller();
        controller.parseBoard("全友家私","");
       /* String html = GetHTML.getHtml("http://search.cs.com.cn/newsSimpleSearch.do?searchword=%E8%B4%B7%E6%AC%BE&time=2&contentType=Content&pn=1","UTF-8");
        Document document = Jsoup.parse(html);
        Elements tables = document.select("div:has(div.hei12)");
        for(Element ele:tables){
            System.out.println(ele);
        }*/
        // System.out.println(java.net.URLEncoder.encode("投资基金", "UTF-8"));
    }
}
