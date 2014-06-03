package com.hhhy.crawler.cs_com_cn;

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
    private final String BASE_URL = "http://search.cs.com.cn/newsSimpleSearch.do?";
    private final String keyWordsLocation = "./keyWords.txt";
    private static String lastTime;
    private static Date lasttime;
    static{
        lasttime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            lasttime = sdf.parse("2014-05-30 07:51:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

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
        for(String keyWord:keyWordsList){

        }
    }

    public static void parseBoard(String keyWord,String BASE_URL){
        String transKey = "";
        try {
            transKey = URLEncoder.encode(keyWord,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = "http://search.cs.com.cn/newsSimpleSearch.do?searchword="+transKey+"&time=2&contentType=Content";
        String html = GetHTML.getHtml(url, "UTF-8");
        html = html.replaceAll("&nbsp;","");
        Document document = Jsoup.parse(html);
        Element tableEles = document.select("table").last();
        String flag = tableEles.text();
        if(flag.contains("[没有检索到任何结果]")){

        }
        else{
            boolean stop = false;
            String page = document.select("span.org12").first().text();
            int pageNum = Integer.parseInt(page);

            ArrayList<Element> tableList = new ArrayList<Element>();

            Elements tables = document.select("div:has(div.hei12)");
            for(Element ele:tables){
                String time = ele.select("tbody").select("td").last().text();
                try {
                    Date date = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse(time);
                    if(date.after(lasttime)){
                        tableList.add(ele);
                    }
                    else{
                        stop = true;
                        break;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
                for(int i=1;!stop && i<pageNum;i++){
                    html = GetHTML.getHtml(url+"&pn="+i,"UTF-8");
                    document = Jsoup.parse(html);
                    tables = document.select("div:has(div.hei12)");
                    for(Element ele:tables){
                        String time = ele.select("tbody").select("td").last().text();
                        try {
                            Date date = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse(time);
                            if(date.after(lasttime)){
                                tableList.add(ele);
                            }
                            else{
                                stop = true;
                                break;
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            lasttime = new Date();
            parsePages(tableList);
        }
    }
    public static void parsePages(ArrayList< Element > tableList){
        for(Element ele:tableList){
            String url = ele.select("tbody").select("td").first().select("a").attr("href");
            String time = ele.select("tbody").select("td").last().text();
            String summary = ele.select("div.hei12").text();
            System.out.println("url:"+url);
            System.out.println("time:"+time);
            System.out.println("summary:"+summary);
            System.out.println("----------------");
            //调接口~~~~~
        }
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        parseBoard("股票价格", "");
       /* String html = GetHTML.getHtml("http://search.cs.com.cn/newsSimpleSearch.do?searchword=%E8%B4%B7%E6%AC%BE&time=2&contentType=Content&pn=1","UTF-8");
        Document document = Jsoup.parse(html);
        Elements tables = document.select("div:has(div.hei12)");
        for(Element ele:tables){
            System.out.println(ele);
        }*/
       // System.out.println(java.net.URLEncoder.encode("投资基金", "UTF-8"));
    }
}
