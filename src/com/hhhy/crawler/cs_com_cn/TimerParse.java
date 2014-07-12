package com.hhhy.crawler.cs_com_cn;

import com.hhhy.crawler.bbs_hexun_com.Controller;

import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Ghost
 * Date: 14-7-12
 * Time: 下午2:28
 * To change this template use File | Settings | File Templates.
 */
public class TimerParse extends TimerTask {
    com.hhhy.crawler.bbs_hexun_com.Controller controller;
    public TimerParse(Controller controller) {
        this.controller = controller;
    }
    @Override
    public void run() {
        for(String keyWord:controller.keyWordsList){
            controller.parseBoard(keyWord,"");
        }
    }
}
