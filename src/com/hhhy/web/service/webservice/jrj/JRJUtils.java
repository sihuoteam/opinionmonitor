package com.hhhy.web.service.webservice.jrj;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;

import chen.bupt.httpclient.NormalHttpClient;
import chen.bupt.httpclient.method.CHttpPost;
import chen.bupt.httpclient.utils.ResponseUtils;

public class JRJUtils {
    
    
    public static DefaultHttpClient login(String name, String psw) throws IOException{
        NormalHttpClient client = new NormalHttpClient();
        CHttpPost post = new CHttpPost("http://sso.jrj.com.cn/sso/ssologin");
//        post.addHeader("Host", "sso.jrj.com.cn");
//        post.addHeader("Origin", "http://sso.jrj.com.cn");
//        post.addHeader("Referer", "http://sso.jrj.com.cn/sso/ssologin?ReturnURL=http://www.jrj.com.cn");
        post.addFormParam("isVerifyCode","false");
        post.addFormParam("ReturnURL","http://www.jrj.com.cn");
        post.addFormParam("LoginID",name);
        post.addFormParam("Passwd",psw);
        HttpResponse respones = client.sendRequest(post.getHttpPost());
        System.out.println(ResponseUtils.getResponseStatus(respones));
        System.out.println(ResponseUtils.getRedirectionURL(respones));
        System.out.println(ResponseUtils.getResponseContent(respones));
        System.out.println(client.getCookie());
        return client.getHttpClient();
    }
    
    public static void main(String[] args) throws IOException{
        login("forest0579","password1");
    }

}
