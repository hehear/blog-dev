package com.blog.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Article implements Serializable {
    private Integer id;

    private String articleName;
    
    private String articleShort;

    private Integer articleTpId;

    private String articleTpNm;

    private Long articleClick;

    private String articleUp;

    private String articleModle;

	private Date articleUpdtdt;

    private String articleContent;

    private String articleContentMarkdown;
    
    private String articleHtmlName;
    
    private Integer articleComments;

    private List<Integer> articleTags;

    private List<String> tags;

    private String articleKeywords;
    
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArticleContentMarkdown() {
		return articleContentMarkdown;
	}

	public void setArticleContentMarkdown(String articleContentMarkdown) {
		this.articleContentMarkdown = articleContentMarkdown;
	}

	public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName == null ? null : articleName.trim();
    }

    public String getArticleShort() {
		return articleShort;
	}

	public void setArticleShort(String articleShort) {
		this.articleShort = articleShort;
	}
    
    public Integer getArticleTpId() {
        return articleTpId;
    }

    public void setArticleTpId(Integer articleTpId) {
        this.articleTpId = articleTpId;
    }
    
    public String getArticleTpNm() {
		return articleTpNm;
	}

	public void setArticleTpNm(String articleTpNm) {
		this.articleTpNm = articleTpNm;
	}

	public Long getArticleClick() {
        return articleClick;
    }

    public void setArticleClick(Long articleClick) {
        this.articleClick = articleClick;
    }

    public String getArticleUp() {
        return articleUp;
    }

    public void setArticleUp(String articleUp) {
        this.articleUp = articleUp == null ? null : articleUp.trim();
    }

    public String getArticleModle() {
        return articleModle;
    }

    public void setArticleModle(String articleModle) {
        this.articleModle = articleModle == null ? null : articleModle.trim();
    }

    public Date getArticleUpdtdt() {
        return articleUpdtdt;
    }

    public void setArticleUpdtdt(Date articleUpdtdt) {
        this.articleUpdtdt = articleUpdtdt;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }

    public List<Integer> getArticleTags() {
        return articleTags;
    }


    public void setArticleTags(List<Integer> articleTags) {

        this.articleTags = articleTags;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getArticleHtmlName() {
		return articleHtmlName;
	}

	public void setArticleHtmlName(String articleHtmlName) {
		this.articleHtmlName = articleHtmlName;
	}
	

	public Integer getArticleComments() {
		return articleComments;
	}

	public void setArticleComments(Integer articleComments) {
		this.articleComments = articleComments;
	}

    public String getArticleKeywords() {
        return articleKeywords;
    }

    public void setArticleKeywords(String articleKeywords) {
        this.articleKeywords = articleKeywords;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", articleName=").append(articleName);
        sb.append(", articleShort=").append(articleShort);
        sb.append(", articleTpId=").append(articleTpId);
        sb.append(", articleClick=").append(articleClick);
        sb.append(", articleUp=").append(articleUp);
        sb.append(", articleModle=").append(articleModle);
        sb.append(", articleUpdtdt=").append(articleUpdtdt);
        sb.append(", articleContent=").append(articleContent);
        sb.append(", articleContentMarkdown=").append(articleContentMarkdown);
        sb.append(", articleHtmlName=").append(articleHtmlName);
        sb.append(", articleComments=").append(articleComments);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}