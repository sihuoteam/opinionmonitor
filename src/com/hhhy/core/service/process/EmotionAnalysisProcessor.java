package com.hhhy.core.service.process;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.MapUtils;
import org.apache.log4j.Logger;

import com.hhhy.db.DBUtils;
import com.hhhy.db.beans.EmotionWord;

/**
 * 判断情感，给出文章直接得到情感分数。
 * 
 * @author chenlingpeng
 * 
 */
public class EmotionAnalysisProcessor {
    private static final Logger logger = Logger.getLogger(EmotionAnalysisProcessor.class);
    private static Map<String, Integer> emotionWords = new HashMap<String, Integer>();
    private static Set<EmotionWord> prorNegWords = new HashSet<EmotionWord>();
    private static List<String> negWords;

    static {
        init();
    }

    private static void init() {
        try {
            List<EmotionWord> tmp = DBUtils.getEmotionWords();
            for (EmotionWord word : tmp) {
                if (word.getVal() < -1) {
                    prorNegWords.add(word);
                } else {
                    emotionWords.put(word.getWord(), word.getVal());
                }
            }
            negWords = DBUtils.loadNegWords();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    public static int emotionParser(List<String> words) {
        int score = 0;
        for (String word : words) {
            score += emotionParser(word);
        }
        return score;
        // emotionWords.
    }

    public static int emotionParser(String word) {
        int score = MapUtils.getInteger(emotionWords, word, 0);
        if(score!=0){
            logger.info("emotion rec for "+word);
        }
        return score;
    }

    // spec for title analysis
    public static int unSegEmotionParser(String title) {
    	String tmp = title;
        int score = 0;
        for(EmotionWord word:prorNegWords){
            if(tmp.contains(word.getWord())){
                score+=word.getVal();
                tmp.replace(word.getWord(), "");
                logger.info("emotion rec in title for "+word.getWord());
            }
        }
        for(String neg:negWords){
        	if(tmp.contains(neg)){
        		return -score;
        	}
        }
        return score;
    }
}
