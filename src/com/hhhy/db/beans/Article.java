package com.hhhy.db.beans;

/**
 * 网页文章
 * 
 * @author chenlingpeng
 * 
 */
public class Article {
    private String title;//not null
    private String summary;//may be null
    private String content;//may be null
    private String time;//not null
    private String url;//not null
    private String website;//name of the website

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

    @Override
    public String toString() {
        return "Article [content=" + content + ", summary=" + summary
                + ", time=" + time + ", title=" + title + ", url=" + url
                + ", website=" + website + "]";
    }
}
