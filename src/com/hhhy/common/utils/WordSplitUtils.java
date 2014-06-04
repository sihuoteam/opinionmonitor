package com.hhhy.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

public class WordSplitUtils {
    
    public static List<String> splitWords(String sentence){
        List<Term> terms = ToAnalysis.paser(sentence);
        List<String> words = new ArrayList<String>();
        for(Term term:terms){
            words.add(term.getName());
        }
        return words;
    }
   
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println();
    }

}
