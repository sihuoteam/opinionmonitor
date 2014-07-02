package com.hhhy.crawler.www_chinacenn_com;

/**
 * Created with IntelliJ IDEA.
 * User: Ghost
 * Date: 14-7-2
 * Time: 下午2:44
 * To change this template use File | Settings | File Templates.
 */

import com.hhhy.crawler.util.ContentFilter;
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
import java.util.ArrayList;

public class Controller {
    private final String BASE_URL = "http://www.chinacenn.com/SerchList.aspx";
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

        String html = GetHTML.getHtml("http://www.chinacenn.com/SerchList.aspx?Strtitle="+transKey, "UTF-8");

        html = html.replaceAll("&nbsp;","");
        Document document = Jsoup.parse(html);

        String flag = document.select("form#form1").select("div#y1_t4").select("div.y2t4_right").select("div.y2t4_wz").text();
        if(flag.equals("")){
            //Todo ??
            System.out.println("nothing have found...");
        }
        else{
            Elements tableEles = document.select("form#form1").select("div#y1_t4").select("div.y2t4_right").select("div.y2t4_wz").select("div.y2_tpp1");
            ArrayList<Element> tableList = new ArrayList<Element>();
            for(Element ele:tableEles){
                tableList.add(ele);
            }
            parsePages(tableList);
        }
    }
    public static void parsePages(ArrayList< Element > tableList){
        for(Element ele:tableList){
            String title = ele.select("div.y2_wz11").select("h4.STYLE5").select("a").text();
            if(!ContentFilter.redundant(spyHistory, title)){
                String time = FormatTime.getCurrentFormatTime();
                String summary = ele.select("div.y2_wz11").select("h6").text();
                String url = "http://www.chinacenn.com/"+ele.select("div.y2_wz11").select("h4.STYLE5").select("a").attr("href");
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
        parseBoard("习近平","");
       /* String html = GetHTML.getHtml("http://search.cs.com.cn/newsSimpleSearch.do?searchword=%E8%B4%B7%E6%AC%BE&time=2&contentType=Content&pn=1","UTF-8");
        Document document = Jsoup.parse(html);
        Elements tables = document.select("div:has(div.hei12)");
        for(Element ele:tables){
            System.out.println(ele);
        }*/
        // System.out.println(java.net.URLEncoder.encode("投资基金", "UTF-8"));
    }
}

