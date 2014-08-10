package com.hhhy.db.beans;

/**
 * 关键词类
 * 对应a_keyword表
 * @author chenlingpeng
 * 
 */
public class KeyWord {
    private int id;
    private int uid;
    private String word;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}
