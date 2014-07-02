package com.hhhy.crawler.www_eeo_com_cn;

import com.hhhy.crawler.finance_ifeng_com.tool;
import com.hhhy.crawler.util.ContentFilter;
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
 * Date: 14-6-9
 * Time: 上午11:15
 * To change this template use File | Settings | File Templates.
 */
public class Controller {
    private final String BASE_URL = "http://app.eeo.com.cn/?app=search&controller=index&action=searchtitle";
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
        for(String keyWord:keyWordsList){
            //Todo
            parseBoard(keyWord,BASE_URL);
        }
    }

    public static void parseBoard(String keyWord,String BASE_URL){
        String transKey = "";
        try {
            transKey = URLEncoder.encode(keyWord, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HashMap<String,String> param = new HashMap<String, String>();
        param.put("t",keyWord);
        String html = GetHTML.postHtml(BASE_URL,"UTF-8",param);
        System.out.println(html);

      /*  html = html.replaceAll("&nbsp;","");
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
        }*/
    }
    public static void parsePages(ArrayList< Element > tableList){
        for(Element ele:tableList){
            String title = ele.select("p").first().text();
            if(!ContentFilter.redundant(spyHistory, title)){
                String orgTime = ele.select("p").get(2).text();
                String time = tool.getTimeStr(orgTime);
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
    public static void main(String[] args){
        parseBoard("国债","http://app.eeo.com.cn/?app=search&controller=index&action=searchtitle");
    }
}
