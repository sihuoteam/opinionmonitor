package com.hhhy.crawler.finance_qq_com;

import com.hhhy.crawler.finance_ifeng_com.Controller;

import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Ghost
 * Date: 14-7-14
 * Time: 上午10:09
 * To change this template use File | Settings | File Templates.
 */
public class TimerParse extends TimerTask {
    com.hhhy.crawler.finance_ifeng_com.Controller controller;
    public TimerParse(Controller controller) {
        this.controller = controller;
    }
    @Override
    public void run() {
        System.out.println("a new round");
        for(String keyWord:controller.keyWordsList){
            controller.parseBoard(keyWord,"");
        }
    }
}