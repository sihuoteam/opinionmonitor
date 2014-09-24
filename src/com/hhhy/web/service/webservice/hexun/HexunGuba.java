package com.hhhy.web.service.webservice.hexun;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ghost on 2014/9/18 0018.
 */
public class HexunGuba {
    String url;
    private static HttpGet setHttpGet(String url){
        HttpGet httpGet = null;
        httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent",
                "Mozilla/5.0 (Windows; U; Windows NT 5.2) Gecko/2008070208 Firefox/3.0.1");
        httpGet.setHeader("Referer","http://reg.hexun.com/rest/loginbox.aspx?post=1&gourl=http://guba.hexun.com/");
        httpGet.setHeader("Host","reg.hexun.com");
        return httpGet;
    }
    private static HttpPost setHttpPost(String url,Map<String,String> headParams){
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
        if(headParams!=null){
            Iterator<Map.Entry<String,String>> iterator = headParams.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String,String> entry = iterator.next();
                httpPost.setHeader(entry.getKey(),entry.getValue());
            }
        }


        return httpPost;
    }
    private static HttpPost postForm(String url,Map<String,String> param,Map<String, String> headParams){
        HttpPost httpPost = setHttpPost(url,headParams);
        Iterator<Map.Entry<String,String>> iterator = param.entrySet().iterator();
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        while (iterator.hasNext()){
            Map.Entry<String,String> entry = iterator.next();
            nvps.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));

        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return httpPost;
    }
    public boolean HexunGubaHuiTie(String username, String password, String tiebaID, String tieziID, String huifu) throws IOException {

        url = "https://reg.hexun.com/login.aspx";
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();

        HashMap<String, String> params = new HashMap<String, String>();

        params.put("LoginStateAuto","on");
        params.put("TextBoxPassword",password);
        params.put("TextBoxUserName",username);
        params.put("fromhost","guba.hexun.com");
        params.put("gourl","on");
        params.put("hiddenReferrer","http://guba.hexun.com/");
        params.put("submitsign","1");

        HashMap<String, String> headParams= new HashMap<String, String>();
        headParams.put("Referer","http://reg.hexun.com/rest/loginbox.aspx?post=1&gourl=http://guba.hexun.com/");
        headParams.put("Host", "reg.hexun.com");


        HttpPost httpPost = postForm(url,params,headParams);
        HttpResponse httpResponse = defaultHttpClient.execute(httpPost);
        int code = httpResponse.getStatusLine().getStatusCode();

        CookieStore cookie = defaultHttpClient.getCookieStore();
        List<Cookie> cookieList = cookie.getCookies();
        for(Cookie cookie1:cookieList){
            System.out.println(cookie1.getName());
            System.out.println(cookie1.getPath());
            System.out.println(cookie1.getComment());
            System.out.println(cookie1.getCommentURL());
            System.out.println(cookie1.getDomain());
            System.out.println(cookie1.getValue());
            System.out.println("-----------------------------");
        }


        String postUrl = "http://guba.hexun.com/PostComment.aspx";
        HashMap<String,String> params2 = new HashMap<String, String>();
        String targetUrl = "http://guba.hexun.com/"+tiebaID+",guba,"+tieziID+".html";
        String[] articleIds = targetUrl.split(",");
        int len = articleIds.length;
        String articleId = articleIds[len-1].replace(".html","");
        params2.put("AQuote","0");
        params2.put("content", huifu);
        params2.put("ArticleID",articleId);
        params2.put("CC","1");
        params2.put("CQuote","0");


        HashMap<String,String> headParams2 = new HashMap<String, String>();
        headParams2.put("Host","guba.hexun.com");
        headParams2.put("Referer",targetUrl);


        HttpPost httpPost2 = postForm(postUrl,params2,headParams2);
        DefaultHttpClient defaultHttpClient1 = new DefaultHttpClient();
        defaultHttpClient1.setCookieStore(cookie);
        HttpResponse response = defaultHttpClient1.execute(httpPost2);
        int code2 = response.getStatusLine().getStatusCode();
        if(code2==200)
            return true;
        else
            return false;
    }

    public boolean HexunGubFatie(String username, String password,String tiebaID, String title, String content) throws IOException {

        url = "https://reg.hexun.com/login.aspx";
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();

        HashMap<String, String> params = new HashMap<String, String>();

        params.put("LoginStateAuto","on");
        params.put("TextBoxPassword",password);
        params.put("TextBoxUserName",username);
        params.put("fromhost","guba.hexun.com");
        params.put("gourl","on");
        params.put("hiddenReferrer","http://guba.hexun.com/");
        params.put("submitsign","1");

        HashMap<String, String> headParams= new HashMap<String, String>();
        headParams.put("Referer","http://reg.hexun.com/rest/loginbox.aspx?post=1&gourl=http://guba.hexun.com/");
        headParams.put("Host", "reg.hexun.com");


        HttpPost httpPost = postForm(url,params,headParams);
        HttpResponse httpResponse = defaultHttpClient.execute(httpPost);
        int code = httpResponse.getStatusLine().getStatusCode();

        CookieStore cookie = defaultHttpClient.getCookieStore();
        List<Cookie> cookieList = cookie.getCookies();
        for(Cookie cookie1:cookieList){
            System.out.println(cookie1.getName());
            System.out.println(cookie1.getPath());
            System.out.println(cookie1.getComment());
            System.out.println(cookie1.getCommentURL());
            System.out.println(cookie1.getDomain());
            System.out.println(cookie1.getValue());
            System.out.println("-----------------------------");
        }



        String getTopicIdUrl = "http://guba.hexun.com/search/ResultAll.aspx?sw="+tiebaID;

        String html = GetHTML.getHtml(getTopicIdUrl,"gb2312");
        String regex = "TopicID=(\\d+)";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(html);
        String topicId = null;
        if(matcher.find()){
            topicId = matcher.group(1);
        }

        if(topicId==null)
            return false;
        else{
            String targetUrl = "http://guba.hexun.com/"+tiebaID+",guba.html";
            String postUrl = "http://guba.hexun.com/PostArticle.aspx";
            HashMap<String,String> params2 = new HashMap<String, String>();

            params2.put("content",content);
            params2.put("title",title);
            params2.put("topicid",topicId);

            HashMap<String,String> headParams2 = new HashMap<String, String>();
            headParams2.put("Host","guba.hexun.com");
            headParams2.put("Referer",targetUrl);


            HttpPost httpPost2 = postForm(postUrl,params2,headParams2);
            DefaultHttpClient defaultHttpClient1 = new DefaultHttpClient();
            defaultHttpClient1.setCookieStore(cookie);
            HttpResponse response = defaultHttpClient1.execute(httpPost2);
            int code2 = response.getStatusLine().getStatusCode();
            if(code2==200){
                return true;
            }
            else
                return false;
        }
    }

    public static void main(String[] args) throws IOException {
        HexunGuba e = new HexunGuba();

        e.HexunGubaHuiTie("ghostinmatrix", "19901109abc", "601607", "19492488", "强势顶！");
        e.HexunGubFatie("ghostinmatrix","19901109abc", "002371","顶七星电子y", "y很有潜力，顶啊！！");

    }
}
