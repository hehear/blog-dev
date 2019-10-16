package com.blog.model;

import java.io.Serializable;
import java.util.Date;

public class ArticleTagRltn implements Serializable {
    private Integer id;

    private Integer tagId;
    
    private Integer articleId;

    private Date updtTm;

    private static final long serialVersionUID = 1L;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Date getUpdtTm() {
        return updtTm;
    }

    public void setUpdtTm(Date updtTm) {
        this.updtTm = updtTm;
    }
}