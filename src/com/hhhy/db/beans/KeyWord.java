package com.hhhy.db.beans;

/**
 * 关键词类
 * 对应a_keyword表
 * @author chenlingpeng
 * 
 */
public class KeyWord {
    private int id;
    private long uid;
    private String keyword;

    public KeyWord(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    

}
