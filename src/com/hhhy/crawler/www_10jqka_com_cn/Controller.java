package com.hhhy.crawler.www_10jqka_com_cn;

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
 * Time: 下午2:55
 * To change this template use File | Settings | File Templates.
 */
public class Controller {
    private final String BASE_URL = "http://search1.hebei.com.cn/m_fullsearch/utf8/full_search.jsp";
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
            try {
                parseBoard(keyWord,BASE_URL);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void parseBoard(String keyWord,String BASE_URL) throws IOException {
        String transKey = "";
        try {
            transKey = URLEncoder.encode(keyWord, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HashMap<String,String> params = new HashMap<String, String>();
        params.put("keywords",keyWord);
        params.put("news_type_id",""+1);
        params.put("x",""+37);
        params.put("y",""+14);

        String html = GetHTML.postHtml("http://search1.hebei.com.cn/m_fullsearch/utf8/full_search.jsp", "UTF-8",params );
        html = html.replaceAll("&nbsp;","");
        Document document = Jsoup.parse(html);

        /*
        搜索关键词是否存在
         */

        Elements flag = document.select("table").first().select("tbody").select("tr");
        if(flag.size()==0){
            //Todo ??
            System.out.println("nothing to show....");
        }
        else{
            Elements tableEles = document.select("table").first().select("tbody").select("tr");
            ArrayList<Element> tableList = new ArrayList<Element>();
            for(Element ele:tableEles){
                tableList.add(ele);
            }
            parsePages(tableList);
        }
    }
    public static void parsePages(ArrayList<Element> tableList){
        for(int i=0;i<tableList.size();i++){
            Element ele1 = tableList.get(i);
            String title = ele1.select("td.searchTitle").text();
            i++;
            Element ele2 =tableList.get(i);
            i++;
            Element ele3 = tableList.get(i);
            if(!ContentFilter.redundant(spyHistory, title)){
                String time = FormatTime.getCurrentFormatTime();
                String summary = ele2.select("td.searchMain").text();
                String url = ele1.select("td.searchTitle").select("a").first().attr("href");
                System.out.println("title:"+title);
                System.out.println("url:"+url);
                System.out.println("time:"+time);
                System.out.println("summary:"+summary);
                System.out.println("website:"+"长城网");
                System.out.println("----------------");
                spyHistory.add(title);
                //调接口~~~~~
            }
        }
    }
    public static void main(String[] args) throws IOException {
        parseBoard("中国人","");
       /* String html = GetHTML.getHtml("http://search.cs.com.cn/newsSimpleSearch.do?searchword=%E8%B4%B7%E6%AC%BE&time=2&contentType=Content&pn=1","UTF-8");
        Document document = Jsoup.parse(html);
        Elements tables = document.select("div:has(div.hei12)");
        for(Element ele:tables){
            System.out.println(ele);
        }*/
        // System.out.println(java.net.URLEncoder.encode("投资基金", "UTF-8"));
    }
}
