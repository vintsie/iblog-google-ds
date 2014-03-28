package com.vint.iblog.datastore.dataclass.ebean;

import com.google.appengine.api.datastore.Key;

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
@SuppressWarnings("unused")
public class G_Article {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private String title;

    @Persistent
    private String blogSeq;

    @Persistent
    private String writer;

    @Persistent
    private int viewCount;

    @Persistent
    private Date createDate;

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
}
