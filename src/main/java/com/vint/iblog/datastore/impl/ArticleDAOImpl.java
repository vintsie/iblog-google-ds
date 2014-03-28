package com.vint.iblog.datastore.impl;

import com.vint.iblog.datastore.PMF;
import com.vint.iblog.datastore.dataclass.ebean.G_Article;
import com.vint.iblog.datastore.define.ArticleDAO;
import org.vint.iblog.common.bean.nor.CBNArticle;

import javax.jdo.PersistenceManager;
import java.util.Date;

/**
 * 基于Google DataStore的持久层实现
 * <p/>
 * Created by Vin on 14-2-14.
 */
public class ArticleDAOImpl implements ArticleDAO {

    @Override
    public int getArticleViewCount(long articleId) throws Exception {
        return 0;
    }

    @Override
    public String postNewArticle(String title, String writer, String blogSeq) throws Exception {
        G_Article article = new G_Article();
        article.setTitle(title);
        article.setWriter(writer);
        article.setCreateDate(new Date(System.currentTimeMillis()));

        PersistenceManager pm = PMF.get().getPersistenceManager();

        try {
            pm.makePersistent(article);
        } finally {
            pm.close();
        }
        return "";
    }

    @Override
    public CBNArticle getArticle(String hCode) throws Exception {
        CBNArticle article = new CBNArticle();
        article.setContent("测试内容 Test Content 111999");
        article.setTitle("马航失联航班 Lost马航失联航班 Lost");
        article.sethCode(hCode);
        return article;
    }
}
