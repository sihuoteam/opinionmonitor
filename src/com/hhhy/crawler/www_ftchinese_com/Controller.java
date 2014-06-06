package com.hhhy.crawler.www_ftchinese_com;

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

/**
 * Created with IntelliJ IDEA.
 * User: Ghost
 * Date: 14-6-4
 * Time: 下午6:38
 * To change this template use File | Settings | File Templates.
 */

class Tupple{
    public Element h3;
    public Element rl;
    public Element rb;
}
public class Controller {

    /**
     * Created with IntelliJ IDEA.
     * User: Ghost
     * Date: 14-6-3
     * Time: 下午5:12
     * To change this template use File | Settings | File Templates.
     */

    private final String BASE_URL = "http://www.ftchinese.com/search/?keys=";
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

        String html = GetHTML.getHtml("http://www.ftchinese.com/search/?keys="+transKey, "UTF-8");

        html = html.replaceAll("&nbsp;","");
        Document document = Jsoup.parse(html);

        String flag = document.select("div#bodywrapper").select("div#body-content-col").select("div.columncontent").text();
        if(flag.contains("对不起，我们的搜索引擎没有找到完全符合您的搜索条件的结果，您可以更换一下关键词")){
            //Todo ??
            System.out.println("duibuqi ");
        }
        else{
            Element tableEles = document.select("div#bodywrapper").select("div#body-content-col").select("div.columncontent").first();
            ArrayList<Tupple> tableList = new ArrayList<Tupple>();
            Elements h3 = tableEles.select("h3.rh");
            Elements rl = tableEles.select("p.rl");
            Elements rb = tableEles.select("p.rb");
            for(int i=0;i<h3.size();i++){
                Tupple tmpT = new Tupple();
                tmpT.h3 = h3.get(i);
                tmpT.rl = rl.get(i);
                tmpT.rb = rb.get(i);
                tableList.add(tmpT);
            }
            parsePages(tableList);            //分别将三个ele组合成Temp。。。
        }
    }
    public static void parsePages(ArrayList< Tupple > tableList){
        for(Tupple tupple:tableList){
            String title =tupple.h3.select("a").text();
            if(!ContentFilter.redundant(spyHistory, title)){
                String time = tupple.rb.select("a").last().text();
                String summary = tupple.rl.text();
                String url = "http://www.ftchinese.com"+tupple.h3.select("a").attr("href");
                System.out.println("----------------");
                System.out.println("title:"+title);
                System.out.println("url:"+url);
                System.out.println("time:"+time);
                System.out.println("summary:"+summary);
                System.out.println("website:"+"21世纪经济报道 ");
                System.out.println("----------------");
                spyHistory.add(title);
                //调接口~~~~~
            }
        }
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        parseBoard("绿茶婊","");
    }
}

