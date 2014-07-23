package com.hhhy.db.beans;

import com.hhhy.db.beans.item.SrcType;

/**
 * 网页文章
 * 
 * @author chenlingpeng
 * 
 */
public class Article {
    private long id;
    private String title;//not null
    private String summary;//may be null
    private String content;//may be null,given url instead in some cases...
    private String time;//may be null
    private String url;//not null
    private String website;//name of the website
//    private SrcType type;
    private int type;   //currently ,spider hasn't find a way to find type.
    private int emotion;
    
    
    
    



    public static void main(String[] args){
        SrcType t=SrcType.elePaper;
        System.out.println(t.getName());
        System.out.println(t.getIndex());
    }

    
    
    public Article(String title, String content, String url, String website) {
        this.title = title;
        this.content = content;
        this.url = url;
        this.website = website;
    }
    
    public Article() {
    }



    public long getId() {
        return id;
    }



    public void setId(long id) {
        this.id = id;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Article [content=" + content + ", summary=" + summary
                + ", time=" + time + ", title=" + title + ", type=" + type
                + ", url=" + url + ", website=" + website + "]";
    }

    
}
