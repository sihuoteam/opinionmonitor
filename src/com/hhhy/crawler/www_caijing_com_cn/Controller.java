package com.hhhy.crawler.www_caijing_com_cn;

import com.hhhy.crawler.util.ContentFilter;
import com.hhhy.crawler.util.FormatTime;
import com.hhhy.crawler.util.GetHTML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Ghost
 * Date: 14-7-6
 * Time: 下午4:50
 * To change this template use File | Settings | File Templates.
 */
public class Controller {
    private final String BASE_URL = "http://search.caijing.com.cn/search.jsp";
    private final String keyWordsLocation = "./keyWords.txt";
    private static ArrayList<String> spyHistory = new ArrayList<String>();//the history record got earlier in today.
   /* private static String lastTime;
    private static Date lasttime;
    static{
        lasttime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            lasttime = sdf.parse("2014-06-03 07:51:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/

    private ArrayList<String> keyWordsList = new ArrayList<String>();
    Controller(){
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
            parseBoard(keyWordsList,BASE_URL);
    }

    public static void parseBoard(ArrayList keyWordsList,String BASE_URL){

        String html = null;
        try {
            html = GetHTML.getHtmlGzip("http://search.caijing.com.cn/search.jsp", "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        html = html.replaceAll("&nbsp;","");
        Document document = Jsoup.parse(html);
        /*
        搜索关键词是否存在
         */
        Elements flag = document.select("div.yaowen").select("li");
        if(flag.size()==0){
            //Todo ??
            System.out.println("nothing to found.....");
        }
        else{
            Elements tableEles = document.select("div.yaowen").select("li");
            ArrayList<Element> tableList = new ArrayList<Element>();
            for(Element ele:tableEles){
                tableList.add(ele);
            }
            parsePages(tableList,keyWordsList);
        }
    }
    public static void parsePages(ArrayList<Element> tableList,ArrayList<String> keyWordsList){
        for(Element ele:tableList){
            String title = ele.select("a").text();
            if(!ContentFilter.redundant(spyHistory, title)){

                for(String keyWord:keyWordsList){
                    if(title.contains(keyWord)){
                        String time = FormatTime.getCurrentFormatTime();
                        String summary = "";
                        String url = ele.select("a").attr("href");
                        System.out.println("title:"+title);
                        System.out.println("url:"+url);
                        System.out.println("time:"+time);
                        System.out.println("summary:"+summary);
                        System.out.println("website:"+"财经网");
                        System.out.println("----------------");
                        spyHistory.add(title);
                        //调接口~~~~~
                        break;
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        ArrayList<String> list = new ArrayList<String>();
        list.add("习近平");
        list.add("青岛");
        parseBoard(list, "");
       /* String html = GetHTML.getHtml("http://search.cs.com.cn/newsSimpleSearch.do?searchword=%E8%B4%B7%E6%AC%BE&time=2&contentType=Content&pn=1","UTF-8");
        Document document = Jsoup.parse(html);
        Elements tables = document.select("div:has(div.hei12)");
        for(Element ele:tables){
            System.out.println(ele);
        }*/
        // System.out.println(java.net.URLEncoder.encode("投资基金", "UTF-8"));
    }
}