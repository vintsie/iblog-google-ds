package com.vint.iblog.datastore.impl;

import com.vint.iblog.datastore.PMF;
import com.vint.iblog.datastore.dataclass.ebean.G_Article;
import com.vint.iblog.datastore.define.ArticleDAO;

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
}
