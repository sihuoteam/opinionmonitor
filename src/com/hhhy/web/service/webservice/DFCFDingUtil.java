package com.hhhy.web.service.webservice;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import chen.bupt.httpclient.NormalHttpClient;
import chen.bupt.httpclient.method.CHttpGet;
import chen.bupt.httpclient.method.CHttpPost;
import chen.bupt.httpclient.utils.InputStreamUtils;
import chen.bupt.httpclient.utils.ResponseUtils;

public class DFCFDingUtil {
    private static final String reg01 = ".*,(.*),(.*)\\.html";
    private static final Pattern pattern01 = Pattern.compile(reg01);

    public static boolean dingtie(String url, String cont) throws IOException {
        System.out.println("ding url: "+url+" "+cont);
        CHttpGet get = new CHttpGet("http://passport.eastmoney.com/guba/AjaxAction.ashx?cb=jQuery18309460038826800883_1411024516265&op=login&dlm=wuxiu9218%40sina.com&mm=920108&vcode=&_=1411024534921");
        Matcher match01 = pattern01.matcher(url);
        String code = null;
        String topic_id = null;
        if(match01.find()){
            code = match01.group(1);
            topic_id = match01.group(2);
        }
        if(code==null || topic_id==null)return false;
        get.addHeader("Referer","http://guba.eastmoney.com/");
        get.addHeader("Host","passport.eastmoney.com");
        NormalHttpClient client = new NormalHttpClient();
        HttpResponse response = client.sendRequest(get.getHttpGet());
        String content = null;
        EntityUtils.consume(response.getEntity());

        CHttpPost post = new CHttpPost("http://guba.eastmoney.com/action.aspx");
        post.addHeader("Host","guba.eastmoney.com");
        post.addHeader("Origin","http://guba.eastmoney.com");
        post.addHeader("Referer",url);
        post.addHeader("X-Requested-With","XMLHttpRequest");
        post.addFormParam("action","review3");
        post.addFormParam("topic_id",topic_id);
        post.addFormParam("huifu_id","");
        post.addFormParam("text",cont);
        post.addFormParam("code",code);
        post.addFormParam("yzm","");
        post.addFormParam("yzm_id","");
        response = client.sendRequest(post.getHttpPost());
        content = InputStreamUtils.entity2String(response.getEntity());
        int status = ResponseUtils.getResponseStatus(response);
        EntityUtils.consume(response.getEntity());
//        System.out.println("content: "+content);
//        System.out.println("status: "+ResponseUtils.getResponseStatus(response));
        if(status!=200 || !content.contains("true"))return false;
        return true;
      }
    
    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        dingtie("http://guba.eastmoney.com/news,000050,122193896.html","顶3");
    }

}
