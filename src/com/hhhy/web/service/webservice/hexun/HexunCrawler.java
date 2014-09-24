package com.hhhy.web.service.webservice.hexun;

import com.hhhy.db.beans.PostArt;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ghost on 2014/9/24 0024.
 */
public class HexunCrawler {
    private String urlBase = "http://guba.hexun.com/";
    private List<PostArt> posts = new ArrayList<PostArt>();


    public List<PostArt> parseHexun(String daihao){
        String url = urlBase+daihao+",guba.html";
        String html = GetHTML.getHtml(url,"gb2312");
        System.out.println(html);
        Document document = Jsoup.parse(html);
        Elements dls = document.select("div#topiclist").select("dl");
        for(Element ele:dls){
            if(ele.select("dt").select("a").size()!=0){
                PostArt postArt = new PostArt();
                postArt.setTitle(ele.select("dt").select("a").text());
                postArt.setUrl(ele.select("dt").select("a").attr("href"));
                posts.add(postArt);
            }
        }
        return posts;
    }
}
