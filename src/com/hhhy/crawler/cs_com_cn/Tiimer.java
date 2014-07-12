package com.hhhy.crawler.cs_com_cn;

import com.hhhy.crawler.bbs_hexun_com.Controller;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Ghost
 * Date: 14-7-12
 * Time: 下午1:43
 * To change this template use File | Settings | File Templates.
 */
public class Tiimer extends TimerTask {
    com.hhhy.crawler.bbs_hexun_com.Controller controller;

    public Tiimer(com.hhhy.crawler.bbs_hexun_com.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        this.controller.spyHistory.clear();
        this.controller.keyWordsList.clear();

        File keyFile = new File(this.controller.keyWordsLocation);
        try {
            BufferedReader br = new BufferedReader(new FileReader(keyFile));
            String line = "";
            while((line = br.readLine())!=null){
                this.controller.keyWordsList.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String keyWord:controller.keyWordsList){
            //Todo
            controller.parseBoard(keyWord,"");
        }
    }
    public static void main(String[] args){
        Timer hexunTimer = new Timer();
        hexunTimer.schedule(new Tiimer(new Controller()),0,60000);
    }
}
