package com.hhhy.crawler;

import com.hhhy.crawler.util.GetHTML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class PageCrawler {
    /**
     * @param charSet
     * @param url
     * @param tag
     */
    public String getContent(String tag,String url,String charSet){
        String html = GetHTML.getHtml(url,charSet);
        Document document = Jsoup.parse(html);
        String content = document.select(tag).text();
        return content;
    }
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
      /*  String transKey = URLEncoder.encode("王小川", "utf-8");
        System.out.println(transKey);*/
        System.out.println(URLDecoder.decode("%C7%EB%CA%E4%C8%EB%D7%F7%D5%DF%B9%D8%BC%FC%D7%D6","gb2312"));
        //System.out.println(new String((new BASE64Decoder()).decodeBuffer("%u738B%u5C0F%u5DDD"),"gb2312"));
        /*String html = GetHTML.getHtml("http://guba.sina.com.cn/?type=1&s=search&key2=%C7%EB%CA%E4%C8%EB%C6%B4%D2%F4%2F%B4%FA%C2%EB%2F%C3%FB%B3%C6&key1=%B9%C9%C6%B1&key3=%C7%EB%CA%E4%C8%EB%D7%F7%D5%DF%B9%D8%BC%FC%D7%D6","gb2312");
        System.out.println(html);*/

    }

}
