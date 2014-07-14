package com.hhhy.web.service.process;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.EmotionWord;
import com.sun.org.apache.commons.collections.MapUtils;


/**
 * 判断情感，给出文章直接得到情感分数。
 * @author chenlingpeng
 *
 */
public class EmotionAnalysisProcessor {
    private static final Logger logger = Logger.getLogger(EmotionAnalysisProcessor.class);
    private static Map<String, Integer> emotionWords = new HashMap<String, Integer>();
    static{
        init();
    }
    
    private static void init(){
        try {
            List<EmotionWord> tmp = DBUtils.getEmotionWords();
            for(EmotionWord word:tmp){
                emotionWords.put(word.getWord(), word.getVal());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }
    
    public static int emotionParser(List<String> words){
        int score = 0;
        for(String word:words){
            score+=emotionParser(word);
        }
        return score;
//        emotionWords.
    }
    
    public static int emotionParser(String word){
        int score = MapUtils.getInteger(emotionWords, word, 0);
        return score;
    }
    
}
