package com.hhhy.crawler.bbs_p5w_net;

import com.hhhy.crawler.util.FormatTime;
import com.hhhy.crawler.util.GetHTML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
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
    private final String BASE_URL = "http://search.discuz.qq.com/f/search?q=%E8%82%A1%E7%A5%A8%E4%BB%B7%E6%A0%BC&sId=5407638&ts=1405320195&mySign=9f5d7f39&menu=1&orderField=default&rfh=1&qs=txt.shome.a";
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
            transKey = URLEncoder.encode(keyWord, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String html = GetHTML.getHtml("http://search.discuz.qq.com/f/search?q="+transKey+"&sId=5407638&ts=1405320195&mySign=9f5d7f39&menu=1&orderField=default&rfh=1&qs=txt.shome.a", "utf-8");

        html = html.replaceAll("&nbsp;","");
        Document document = Jsoup.parse(html);
        /*
        搜索关键词是否存在
         */
        Elements flag = document.select("div.result").select("span#result-items").select("ul").select("li");
        if(flag.size()==0){
            //Todo ??
            System.out.println("nothing to found.....");
        }
        else{
            Elements tableEles =  document.select("div.result").select("span#result-items").select("ul").select("li");
            ArrayList<Element> tableList = new ArrayList<Element>();
            for(Element ele:tableEles){
                tableList.add(ele);
            }
            parsePages(tableList);
        }
    }
    public void parsePages(ArrayList<Element> tableList){
        for(Element ele:tableList){
            String title = ele.select("h3.title").select("a").text();
            String time = Subutils.getTime(ele.select("p.meta").last().text());
            if(FormatTime.isAfterToday(time)){//"2015-11-11 00:00:00"
                if(!this.spyHistory.contains(title)){

                    String summary = ele.select("p.content").text();
                    String url = ele.select("h3.title").select("a").attr("href");
                    System.out.println("title:" + title);
                    System.out.println("url:" + url);
                    System.out.println("time:" + time);
                    System.out.println("summary:" + summary);
                    System.out.println("website:" + "全景社区");
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
