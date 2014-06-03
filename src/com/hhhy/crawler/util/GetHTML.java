package com.hhhy.crawler.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Ghost
 * Date: 14-4-17
 * Time: 下午4:20
 * To change this template use File | Settings | File Templates.
 */
public class GetHTML {
    private static HttpGet setHttpGet(String url){
        HttpGet httpGet = null;
        httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent",
                "Mozilla/5.0 (Windows; U; Windows NT 5.2) Gecko/2008070208 Firefox/3.0.1");
        return httpGet;
    }
    public static String getHtml(String url,String charSet){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = setHttpGet(url);
        String back = "";
        try {
            HttpResponse response = httpClient.execute(httpGet);
            if (response != null) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == HttpStatus.SC_OK) { // 2XX状态码
                    HttpEntity entity = response.getEntity();
                    String html = InputStreamUtils.inputStream2String(entity.getContent(),charSet);
                    back = html;
                }
            }
            else
                back = "error";
        } catch (ClientProtocolException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return back;
    }
    public static void main(String[] args){

    }
}
