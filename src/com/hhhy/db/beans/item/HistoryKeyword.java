package com.hhhy.db.beans.item;



public class HistoryKeyword{
	private long id;
	private String key;
	private int flag;
	private long begin;
	private long end;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public int getFlag() {
        return flag;
    }
    public void setFlag(int flag) {
        this.flag = flag;
    }
    public long getBegin() {
        return begin;
    }
    public void setBegin(long begin) {
        this.begin = begin;
    }
    public long getEnd() {
        return end;
    }
    public void setEnd(long end) {
        this.end = end;
    }
	
	
}