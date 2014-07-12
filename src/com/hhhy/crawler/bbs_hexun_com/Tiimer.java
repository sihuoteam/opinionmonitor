package com.hhhy.crawler.bbs_hexun_com;

import java.io.*;
import java.util.ArrayList;
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

    @Override
    public void run() {
        this.spyHistory.clear();
        this.keywordsList.clear();

        File keyFile = new File(keyWordsLocation);
        try {
            BufferedReader br = new BufferedReader(new FileReader(keyFile));
            String line = "";
            while((line = br.readLine())!=null){
                this.keywordsList.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String keyWord:keywordsList){
            //Todo
            parseBoard(keyWord,BASE_URL);
        }
    }
}
