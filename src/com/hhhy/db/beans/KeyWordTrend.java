package com.hhhy.db.beans;


/**
 * 对关键词各项统计类
 * 对应表a_keywordtrend
 * 暂时直接用webpage表做统计
 * @author chenlingpeng
 *
 */
public class KeyWordTrend {
private String url;
private String keyword;
private long createtime;
private int type; // 媒体来源
private String website;// 数据网站来源
private int emotion;


}
