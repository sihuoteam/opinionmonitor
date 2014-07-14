package com.hhhy.crawler.www_chinanews_com;


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
 * Time: 下午5:59
 * To change this template use File | Settings | File Templates.
 */
public class Controller {
    public final String BASE_URL = "http://sou.chinanews.com/search.do";
    public final String keyWordsLocation = "./keyWords.txt";
    public ArrayList<String> spyHistory;//the history record got earlier in today.
    public ArrayList<String> keyWordsList;
    Controller(){
        spyHistory = new ArrayList<String>();
        keyWordsList = new ArrayList<String>();
        File keyFile = new File(keyWordsLocation);
        try {
            BufferedReader br = new BufferedReader(new FileReader(keyFile));
            String line = "";
            while((line = br.readLine())!=null){
                keyWordsList.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String keyWord:keyWordsList){
            //Todo
            parseBoard(keyWord,BASE_URL);
        }
    }

    public void parseBoard(String keyWord,String BASE_URL){
        String transKey = "";
        try {
            transKey = URLEncoder.encode(keyWord, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String html = GetHTML.getHtml("http://sou.chinanews.com/search.do?q=" + transKey, "UTF-8");

        html = html.replaceAll("&nbsp;","");
        Document document = Jsoup.parse(html);

        String flag = document.select("table[style]").first().select("tbody").select("div#news_list").select("span").text() ;
        if(flag.contains("对不起，没有找到相关内容，请更换关键字后重试")){
            //Todo ??
            System.out.println("nothing have found...");
        }
        else{
            Elements tableEles = document.select("table[style]").first().select("tbody").select("div#news_list").select("table");
            ArrayList<Element> tableList = new ArrayList<Element>();
            for(Element ele:tableEles){
                tableList.add(ele);
            }
            parsePages(tableList);
        }
    }
    public void parsePages(ArrayList< Element > tableList){
        for(Element ele:tableList){
            String title = ele.select("li.news_title").select("a").text();
            if(!this.spyHistory.contains(title)){
                String time = FormatTime.getCurrentFormatTime();
                String summary = ele.select("li.news_content").text();
                String url = ele.select("li.news_title").select("a").attr("href");
                System.out.println("title:"+title);
                System.out.println("url:"+url);
                System.out.println("time:"+time);
                System.out.println("summary:"+summary);
                System.out.println("website:"+"中国企业新闻");
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
