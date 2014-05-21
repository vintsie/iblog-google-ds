package com.vint.iblog.datastore.impl;

import com.vint.iblog.datastore.PMF;
import com.vint.iblog.datastore.dataclass.ebean.G_Article;
import com.vint.iblog.datastore.define.ArticleDAO;
import org.vint.iblog.common.bean.nor.CBNArticle;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.*;

/**
 * 基于Google DataStore的持久层实现
 * <p/>
 * Created by Vin on 14-2-14.
 */
public class ArticleDAOImpl implements ArticleDAO {

    @Override
    public void deleteArticle(String hCode) throws Exception {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            Query query = pm.newQuery(G_Article.class);
            query.setFilter("blogSeq == hexCode");
            query.declareParameters("String hexCode");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("hexCode", hCode);
            query.deletePersistentAll(params);
        } finally {
            pm.close();
        }
    }

    @Override
    public int getArticleViewCount(long articleId) throws Exception {
        return 0;
    }

    @Override
    public void modifyArticle(CBNArticle article) throws Exception {
        G_Article storedArticle = getInnerArticle(article.getTitle(), article.getRepoInfo());
        if (null != storedArticle) {
            storedArticle.setContent(article.getContent());
            storedArticle.setMdContent(article.getMarkdownContent());
            storedArticle.setSha(article.getSha());
            persistence(storedArticle);
        }
    }

    @Override
    public String postNewArticle(String title, String content, String writer, String blogSeq) throws Exception {
        persistence(newArticle(title, content, null, null, writer, blogSeq));
        return "";
    }

    private G_Article newArticle(String title, String content, String mdContent,
                                 String sha, String writer, String blogSeq) {
        G_Article article = new G_Article();
        article.setTitle(title);
        article.setWriter(writer);
        article.setCreateDate(new Date());
        article.setContent(content);
        article.setMdContent(mdContent);
        article.setSha(sha);
        article.setBlogSeq(blogSeq);
        return article;
    }

    @Override
    public CBNArticle getArticle(String hCode) throws Exception {
        return getInnerArticle(hCode).transferTo();
    }

    @Override
    public CBNArticle getArticle(String title, String repoInfo) throws Exception {
        G_Article innerA = getInnerArticle(title, repoInfo);
        return null == innerA ? null : innerA.transferTo();
    }


    /**
     * 根据16进制字符串查询文章对象，内部对象
     *
     * @param hexCode 逻辑上的唯一标识
     * @return {@link com.vint.iblog.datastore.dataclass.ebean.G_Article}
     * @throws Exception
     */
    private G_Article getInnerArticle(String hexCode) throws Exception {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        G_Article article = null;
        try {
            Query query = pm.newQuery(G_Article.class);
            query.setFilter("blogSeq == hexCode");
            query.declareParameters("String hexCode");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("hexCode", hexCode);
            Object obj = query.executeWithMap(params);
            if (null != obj) {
                List resultSet = (List) obj;
                if (!resultSet.isEmpty()) {
                    article = ((G_Article) resultSet.get(0));
                }
            }
        } finally {
            pm.close();
        }
        return article;
    }


    /**
     * 根据文章标题和代码仓库信息查询持久化对象
     *
     * @param title    标题
     * @param repoInfo 仓库信息
     * @return {@link com.vint.iblog.datastore.dataclass.ebean.G_Article}
     * @throws Exception
     */
    private G_Article getInnerArticle(String title, String repoInfo) throws Exception {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        G_Article article = null;
        try {
            Query query = pm.newQuery(G_Article.class);
            query.setFilter("title == t1 && repoInfo == ri");
            query.declareParameters("String t1, String ri");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("t1", title);
            params.put("ri", repoInfo);
            Object obj = query.executeWithMap(params);
            if (null != obj) {
                List resultSet = (List) obj;
                if (!resultSet.isEmpty()) {
                    article = ((G_Article) resultSet.get(0));
                }
            }
        } finally {
            pm.close();
        }
        return article;
    }

    @Override
    public List<CBNArticle> getArticles(int pageNum, int pageSize) throws Exception {
        if (pageNum < 1) {
            throw new Exception("Unsupported page number:" + pageNum);
        }
        if (pageSize < 1) {
            throw new Exception("Unsupported page size:" + pageSize);
        }
        List<CBNArticle> data = new ArrayList<CBNArticle>();
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            Query query = pm.newQuery(G_Article.class);
            query.setRange((pageNum - 1) * pageSize, pageNum * pageSize);
            List resultSet = (List) query.execute();
            if (null != resultSet && !resultSet.isEmpty()) {
                for (Object obj : resultSet) {
                    data.add(((G_Article) obj).transferTo());
                }
            }
        } finally {
            pm.close();
        }
        return data;
    }

    @Override
    public String postNewArticle(CBNArticle article) throws Exception {
        G_Article gArticle = G_Article.transferFrom(article);
        persistence(gArticle);
        return gArticle.getBlogSeq();
    }

    @Override
    public void saveArticles(List<CBNArticle> articles) throws Exception {
        List<G_Article> gArticles = new ArrayList<G_Article>();
        for (CBNArticle article : articles) {
            gArticles.add(G_Article.transferFrom(article));
        }
        if (gArticles.size() > 0) {
            persistence(gArticles.toArray(new G_Article[gArticles.size()]));
        }
    }

    private void persistence(G_Article article) throws Exception {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            if (null == article.getCreateDate()) {
                article.setCreateDate(new Date(System.currentTimeMillis()));
            }
            pm.makePersistent(article);
        } finally {
            pm.close();
        }
    }

    private void persistence(G_Article[] articles) throws Exception {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            for (G_Article a : articles) {
                if (null == a.getCreateDate()) {
                    a.setCreateDate(new Date(System.currentTimeMillis()));
                }
            }
            pm.makePersistentAll(articles);
        } finally {
            pm.close();
        }
    }
}
