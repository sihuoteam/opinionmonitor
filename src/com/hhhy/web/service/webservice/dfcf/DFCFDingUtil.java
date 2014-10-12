package com.hhhy.web.service.webservice.dfcf;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hhhy.db.beans.PostArt;

import chen.bupt.httpclient.NormalHttpClient;
import chen.bupt.httpclient.method.CHttpGet;
import chen.bupt.httpclient.method.CHttpPost;
import chen.bupt.httpclient.utils.InputStreamUtils;
import chen.bupt.httpclient.utils.ResponseUtils;

public class DFCFDingUtil {
    private static final String reg01 = ".*,(.*),(.*)\\.html";
    private static final Pattern pattern01 = Pattern.compile(reg01);

    private static NormalHttpClient login(String username, String psw) throws IOException {
        username = URLEncoder.encode(username, "utf-8");
        psw = URLEncoder.encode(psw, "utf-8");
        System.out.println(username);
        System.out.println(psw);
        CHttpGet get = new CHttpGet(
                "http://passport.eastmoney.com/guba/AjaxAction.ashx?cb=jQuery18309460038826800883_1411024516265&op=login&dlm="+username+"&mm="+psw+"&vcode=&_=1411024534921");
        get.addHeader("Referer", "http://guba.eastmoney.com/");
        get.addHeader("Host", "passport.eastmoney.com");
        NormalHttpClient client = new NormalHttpClient();
        HttpResponse response = client.sendRequest(get.getHttpGet());
        EntityUtils.consume(response.getEntity());
        return client;
    }

    public static boolean dingtie(String url, String cont) throws IOException {
        Matcher match01 = pattern01.matcher(url);
        String code = null;
        String topic_id = null;
        if (match01.find()) {
            code = match01.group(1);
            topic_id = match01.group(2);
        }
        if (code == null || topic_id == null)
            return false;
        NormalHttpClient client = login("wuxiu9218@sina.com","920108");

        CHttpPost post = new CHttpPost("http://guba.eastmoney.com/action.aspx");
        post.addHeader("Host", "guba.eastmoney.com");
        post.addHeader("Origin", "http://guba.eastmoney.com");
        post.addHeader("Referer", url);
        post.addHeader("X-Requested-With", "XMLHttpRequest");
        post.addFormParam("action", "review3");
        post.addFormParam("topic_id", topic_id);
        post.addFormParam("huifu_id", "");
        post.addFormParam("text", cont);
        post.addFormParam("code", code);
        post.addFormParam("yzm", "");
        post.addFormParam("yzm_id", "");
        HttpResponse response = client.sendRequest(post.getHttpPost());
        String content = InputStreamUtils.entity2String(response.getEntity());
        int status = ResponseUtils.getResponseStatus(response);
        EntityUtils.consume(response.getEntity());
        client.releaseConnection();
        if (status != 200 || !content.contains("true"))
            return false;
        return true;
    }

    public static boolean fatie(String number, String title, String content)
            throws IOException {
        NormalHttpClient client = login("wuxiu9218@sina.com","920108");
        CHttpPost post = new CHttpPost("http://guba.eastmoney.com/action.aspx");
        // NormalHttpClient client = new NormalHttpClient();
        String host = "guba.eastmoney.com";
        String origin = "http://guba.eastmoney.com";
        String ref = "http://guba.eastmoney.com/list," + number + ".html";
        post.addHeader("Host", host);
        post.addHeader("Origin", origin);
        post.addHeader("Referer", ref);
        post.addHeader("X-Requested-With", "XMLHttpRequest");
        post.addFormParam("action", "add3");
        post.addFormParam("yuan_id", "0");
        post.addFormParam("title", title);
        post.addFormParam("text", content);
        post.addFormParam("code", number);
        post.addFormParam("pdf", "");
        post.addFormParam("pic", "");
        post.addFormParam("postvalid", "1");
        post.addFormParam("yzm_id", "");
        post.addFormParam("yzm", "");
        post.addFormParam("quanxian", "0");
        try {
            HttpResponse response = client.sendRequest(post.getHttpPost());
            String retC = ResponseUtils.getResponseContent(response)
                    .toLowerCase();
            int retCode = ResponseUtils.getResponseStatus(response);
            EntityUtils.consume(response.getEntity());
            client.releaseConnection();
            if (retCode == 200 && retC.contains("true")) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<PostArt> getPosts(String code) throws IOException{
        String url = "http://guba.eastmoney.com/list,"+code+".html";
        NormalHttpClient client = new NormalHttpClient();
        CHttpGet httpGet = new CHttpGet(url);
        HttpResponse response = client.sendRequest(httpGet.getHttpGet());
        String content = InputStreamUtils.entity2String(response.getEntity());
        List<PostArt> arts= new ArrayList<PostArt>();
        Document doc = Jsoup.parse(content);
        Elements eles = doc.select(".articleh");
        for(Element ele:eles){
            PostArt art = new PostArt();
            String title = ele.select(".l3").select("a").attr("title");
            String url2 = "http://guba.eastmoney.com/"+ele.select(".l3").select("a").attr("href");
            art.setTitle(title);
            art.setUrl(url2);
            arts.add(art);
        }
        return arts;
    }
    
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
//         dingtie("http://guba.eastmoney.com/news,cjpl,123781934.html","顶3");
//        System.out.println("=======================");
//        System.out.println(fatie("002314", "标题要长啊啊啊啊啊啊啊啊啊啊啊啊啊2", "大家好"));
      List<PostArt> posts= getPosts("002314");
      for(PostArt post:posts){
          System.out.println(post.getTitle()+": "+post.getUrl());
      }
    }

}
