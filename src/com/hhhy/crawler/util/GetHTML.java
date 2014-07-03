package com.hhhy.crawler.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Ghost
 * Date: 14-4-17
 * Time: 下午4:20
 * To change this template use File | Settings | File Templates.
 */
public class GetHTML {
    private static HttpPost setHttpPost(String url){
        HttpPost httpPost = null;
        httpPost = new HttpPost(url);
        httpPost.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");

        return httpPost;
    }

    private static HttpGet setHttpGet(String url){
        HttpGet httpGet = null;
        httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent",
                "Mozilla/5.0 (Windows; U; Windows NT 5.2) Gecko/2008070208 Firefox/3.0.1");
        return httpGet;
    }

    public static String postHtml(String url,String charSet,Map<String,String> param){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = postForm(url,param);
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity rspEntity = null;
        if (httpResponse != null) {
            rspEntity = httpResponse.getEntity();
            String html = null;
            try {
                html = InputStreamUtils.inputStream2String(rspEntity.getContent(),charSet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return html;
        }
        else {
            MyLog.logINFO("httpResponse is null!");
            return "";
        }

    }
    private static HttpPost postForm(String url,Map<String,String> param){
        HttpPost httpPost = setHttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Set<String> keySet = param.keySet();
        for(String key:keySet){
            nvps.add(new BasicNameValuePair(key,param.get(key)));
        }
        try {
            MyLog.logINFO("setting Post Entity");
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return httpPost;
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
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return back;
    }
    public static String getHtmlGzip(String url,String charSet) throws IOException {
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        getMethod.setRequestHeader("Connection", "Keep-Alive");
        getMethod.setRequestHeader("Accept", "*/*");
        getMethod.setRequestHeader("From", "goolebot@googlebot.com");
        getMethod.setRequestHeader("User-Agent",
                "Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html)");
        getMethod.setRequestHeader("Accept-Encoding", "gzip");
        int stateCode = httpClient.executeMethod(getMethod);
        String acceptEncoding = "";
        if(getMethod.getResponseHeader("Content-Encoding")!=null){
            acceptEncoding = getMethod.getResponseHeader("Content-Encoding").getValue();
            if(acceptEncoding.toLowerCase().contains("gzip")){
                GZIPInputStream gzipin = new GZIPInputStream(getMethod.getResponseBodyAsStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(gzipin,charSet));
                String line = "";
                String html = "";
                while((line = br.readLine())!=null)
                    html += line+"\r\n";
                getMethod.releaseConnection();
                return html;
              /*  InputStream is = getMethod.getResponseBodyAsStream();
                GZIPInputStream gzin = new GZIPInputStream(is);
                InputStreamReader isr = new InputStreamReader(gzin, charSet); // 设置读取流的编码格式，自定义编码
                java.io.BufferedReader br = new java.io.BufferedReader(isr);
                String tempbf;
                while ((tempbf = br.readLine()) != null) {
                    sb.append(tempbf);
                    sb.append("\r\n");
                }
                isr.close();
                gzin.close();
                getMethod.abort();
                getMethod.releaseConnection();
                return sb.toString();*/
            }
        }
        return null;
    }
    public static void main(String[] args){

    }
}
