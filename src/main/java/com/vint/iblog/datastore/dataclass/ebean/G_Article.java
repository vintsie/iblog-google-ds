package com.vint.iblog.datastore.dataclass.ebean;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import org.vint.iblog.common.bean.nor.CBNArticle;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import java.util.Date;

/**
 *
 * Created by Vin on 14-2-17.
 */
@PersistenceCapable
public class G_Article {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private String title;
    @Persistent
    private Text content;
    @Persistent
    private Text mdContent;
    @Persistent
    private String sha;
    @Persistent
    private String blogSeq;
    @Persistent
    private String writer;
    @Persistent
    private int viewCount;
    @Persistent
    private Date createDate;
    @Persistent
    private String repoInfo;

    public String getContent() {
        return content.toString();
    }

    public void setContent(String content) {
        this.content = new Text(content);
    }


    public String getMdContent() {
        return mdContent.toString();
    }

    public void setMdContent(String mdContent) {
        this.mdContent = new Text(mdContent);
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public Key getKey() {
        return key;
    }

    public String getBlogSeq() {
        return blogSeq;
    }

    public void setBlogSeq(String blogSeq) {
        this.blogSeq = blogSeq;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getRepoInfo() {
        return repoInfo;
    }

    public void setRepoInfo(String repoInfo) {
        this.repoInfo = repoInfo;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public CBNArticle transferTo(){
        CBNArticle a = new CBNArticle();
        a.setContent(this.getContent());
        a.sethCode(this.blogSeq);
        a.setTitle(this.title);
        a.setMarkdownContent(this.getMdContent());
        a.setSha(this.sha);
        a.setRepoInfo(this.repoInfo);
        return a;
    }


    public static G_Article transferFrom(CBNArticle article){
        G_Article gArticle = new G_Article();
        gArticle.blogSeq = article.gethCode();
        gArticle.setContent(article.getContent());
        gArticle.setMdContent(article.getMarkdownContent());
        gArticle.sha = article.getSha();
        gArticle.title = article.getTitle();
        gArticle.repoInfo = article.getRepoInfo();
        return gArticle;
    }

}
