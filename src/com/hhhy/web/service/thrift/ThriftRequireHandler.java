package com.hhhy.web.service.thrift;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.hhhy.common.utils.JsonUtils;
import com.hhhy.core.service.process.ProcessChain;
import com.hhhy.db.beans.Article;
import com.hhhy.web.service.thrift.gencode.HhhyService;

/**
 * Created by lenovo on 8/10/2014.
 */
public class ThriftRequireHandler implements HhhyService.Iface{
    private static final Logger logger = Logger.getLogger(ThriftRequireHandler.class);
    @Override
    public void addArticle(String jsonString) throws TException {
        Article art = JsonUtils.fromJson(jsonString, Article.class);
        // TODO: 
        ProcessChain.process(art);
    }

    @Override
    public String getKeywords() throws TException {
        return null;
    }
}
