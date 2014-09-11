package com.hhhy.db.beans;



public class HistoryBean{
	private long id;
	private int kid;
	private long ctime;
	private String summary;
	private int emotion;
	private String title;
	private String url;
	private String source;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getKid() {
        return kid;
    }
    public void setKid(int kid) {
        this.kid = kid;
    }
    public long getCtime() {
        return ctime;
    }
    public void setCtime(long ctime) {
        this.ctime = ctime;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public int getEmotion() {
        return emotion;
    }
    public void setEmotion(int emotion) {
        this.emotion = emotion;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
	
	
}