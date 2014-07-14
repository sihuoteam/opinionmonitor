package com.hhhy.crawler.guba_sina_com_cn;

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
    private final String BASE_URL = "";
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
            transKey = URLEncoder.encode(keyWord, "gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String html = GetHTML.getHtml("http://guba.sina.com.cn/?type=1&s=search&key2=%C7%EB%CA%E4%C8%EB%C6%B4%D2%F4%2F%B4%FA%C2%EB%2F%C3%FB%B3%C6&key1="+transKey+"&key3=%C7%EB%CA%E4%C8%EB%D7%F7%D5%DF%B9%D8%BC%FC%D7%D6", "gb2312");

        html = html.replaceAll("&nbsp;","");
        Document document = Jsoup.parse(html);
        /*
        搜索关键词是否存在
         */
        Elements flag = document.select("div.blk_list").select("div");
        if(flag.size()==0){
            //Todo ??
            System.out.println("nothing to found.....");
        }
        else{
            Elements tableEles =  document.select("div.blk_list").select("div");
            ArrayList<Element> tableList = new ArrayList<Element>();
            for(Element ele:tableEles){
                tableList.add(ele);
            }
            parsePages(tableList);
        }
    }
    public void parsePages(ArrayList<Element> tableList){
        for(Element ele:tableList){
            String title = ele.select("div.il_txt").select("h4.ilt_tit").select("a").text();
            String time = Subutils.getTime(ele.select("div.ilt_panel").select("div.fl_left").select("a").text());
            if(FormatTime.isAfterToday(time)){//"2015-11-11 00:00:00"
                if(!this.spyHistory.contains(title)){
                    String summary = ele.select("div.il_txt").select("p.ilt_p").text();
                    String url = "http://guba.sina.com.cn"+ele.select("div.il_txt").select("h4.ilt_tit").select("a").attr("href");
                    System.out.println("title:" + title);
                    System.out.println("url:" + url);
                    System.out.println("time:" + time);
                    System.out.println("summary:" + summary);
                    System.out.println("website:" + "新浪股吧");
                    System.out.println("----------------");
                    spyHistory.add(title);
                    //调接口~~~~~
                }
            }
        }
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        Controller controller = new Controller();
        controller.parseBoard("习近平", "");
       /* String html = GetHTML.getHtml("http://search.cs.com.cn/newsSimpleSearch.do?searchword=%E8%B4%B7%E6%AC%BE&time=2&contentType=Content&pn=1","UTF-8");
        Document document = Jsoup.parse(html);
        Elements tables = document.select("div:has(div.hei12)");
        for(Element ele:tables){
            System.out.println(ele);
        }*/
        // System.out.println(java.net.URLEncoder.encode("投资基金", "UTF-8"));
    }
}
