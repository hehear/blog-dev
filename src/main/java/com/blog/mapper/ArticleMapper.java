package com.blog.mapper;

import java.util.List;

import com.blog.model.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.blog.common.PageJson;
import com.blog.model.Article;

public interface ArticleMapper {

    @Delete({
        "delete from article",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into article (ID, ARTICLE_NAME,ARTICLE_SHORT, ",
        "ARTICLE_TP_ID, ARTICLE_CLICK, ",
        "ARTICLE_UP, ARTICLE_MODLE, ",
        "ARTICLE_UPDTDT, ARTICLE_CONTENT,ARTICLE_CONTENT_MARKDOWN,ARTICLE_HTML_NAME,ARTICLE_KEYWORDS)",
        "values (#{id,jdbcType=INTEGER}, #{articleName,jdbcType=VARCHAR},#{articleShort,jdbcType=VARCHAR}, ",
        "#{articleTpId,jdbcType=INTEGER}, #{articleClick,jdbcType=DECIMAL}, ",
        "#{articleUp,jdbcType=CHAR}, #{articleModle,jdbcType=CHAR}, ",
        "#{articleUpdtdt,jdbcType=TIMESTAMP}, #{articleContent,jdbcType=LONGVARCHAR}, #{articleContentMarkdown,jdbcType=LONGVARCHAR}," ,
        "#{articleHtmlName,jdbcType=LONGVARCHAR},#{articleKeywords,jdbcType=VARCHAR})"
    })
    int insert(Article record);

    @Select({
        "select",
        "ID, ARTICLE_NAME, ARTICLE_SHORT, ARTICLE_TP_ID, ARTICLE_CLICK, ARTICLE_UP, ARTICLE_MODLE, ARTICLE_UPDTDT, ",
        "ARTICLE_CONTENT,ARTICLE_CONTENT_MARKDOWN,ARTICLE_HTML_NAME,ARTICLE_KEYWORDS",
        "from article",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    Article selectByPrimaryKey(Integer id);
    
   
    @Select({
    	"<script>",
        "select",
        "a.ID, ARTICLE_NAME, ARTICLE_SHORT, a.ARTICLE_TP_ID, ARTICLE_TP_NM, ARTICLE_CLICK, ARTICLE_UP, ARTICLE_MODLE,",
        " ARTICLE_UPDTDT,ARTICLE_CONTENT,ARTICLE_CONTENT_MARKDOWN,ARTICLE_HTML_NAME,ARTICLE_KEYWORDS ",
        "from blog.ARTICLE a left join blog.ARTICLE_TYPE b",
        " on a.ARTICLE_TP_ID=b.ARTICLE_TP_ID ",
        "where 1=1 ", 
        " <if test='article.id !=null'>AND a.ID = #{article.id}</if>",
        " <if test='article.articleTpId !=null'>AND a.ARTICLE_TP_ID = #{article.articleTpId}</if>",
        " <if test='article.articleUp !=null'>AND ARTICLE_UP = #{article.articleUp}</if>",
        "<choose> ",
        "<when test='article.articleName'>",
        "    <bind name='articleNm' value=\"'%' + article.articleName + '%'\"/>",
        "</when>",
        "<otherwise>",
        "    <bind name='articleNm' value=\"'%%'\"/> ",
        "</otherwise>",
        "</choose>",
        " <if test='article.articleName !=null and !(\"\").equals(article.articleName)'>AND ARTICLE_NAME like #{articleNm}</if>",
        " order by a.ID,ARTICLE_UPDTDT desc ",
        " limit #{page.pageFirstIndex},#{page.pageSize}",
        "</script>"
    })
    
    List<Article> selectByPage(@Param(value = "article")Article article,@Param(value = "page")PageJson page);
    
    @Select({
    	"<script>",
        "select",
        "count(*)",
        "from blog.ARTICLE",
        "where 1=1 ", 
        " <if test='id !=null'>AND ID = #{id}</if>",
        " <if test='articleTpId !=null'>AND ARTICLE_TP_ID = #{articleTpId}</if>",
        " <if test='articleUp !=null'>AND ARTICLE_UP = #{articleUp}</if>",
        "<choose> ",
        "<when test='articleName'>",
        "    <bind name='articleNm' value=\"'%' + articleName + '%'\"/>",
        "</when>",
        "<otherwise>",
        "    <bind name='articleNm' value=\"'%%'\"/> ",
        "</otherwise>",
        "</choose>",
        " <if test='articleName !=null and !(\"\").equals(articleName)'>AND ARTICLE_NAME like #{articleNm}</if>",
        "</script>"
    })
    int selectCounts(Article article);

    @Select({
        "select",
        "ID, ARTICLE_NAME, ARTICLE_SHORT, ARTICLE_TP_ID, ARTICLE_CLICK, ARTICLE_UP, ARTICLE_MODLE, ARTICLE_UPDTDT, ",
        "ARTICLE_CONTENT,ARTICLE_CONTENT_MARKDOWN,ARTICLE_HTML_NAME,ARTICLE_KEYWORDS ",
        "from article"
    })
    List<Article> selectAll();
    
    @Select({
        "select",
        "a.ID, ARTICLE_NAME, ARTICLE_SHORT, ARTICLE_TP_ID, ARTICLE_CLICK, ARTICLE_UP, ARTICLE_MODLE, ARTICLE_UPDTDT, ",
        "ARTICLE_CONTENT,ARTICLE_CONTENT_MARKDOWN,ARTICLE_HTML_NAME,case when  b.count is null then 0 else b.count  end  as ARTICLE_COMMENTS,ARTICLE_KEYWORDS ",
        "from article a left join  (select article_id,count(id) as count from comment group by article_id) b",
        " on a.id=b.article_id  order by a.ARTICLE_UPDTDT desc"
    })
    List<Article> selectList();

    @Update({
        "update article",
        "set ARTICLE_NAME = #{articleName,jdbcType=VARCHAR},",
          "ARTICLE_SHORT = #{articleShort,jdbcType=VARCHAR},",
          "ARTICLE_TP_ID = #{articleTpId,jdbcType=INTEGER},",
          "ARTICLE_CLICK = #{articleClick,jdbcType=DECIMAL},",
          "ARTICLE_UP = #{articleUp,jdbcType=CHAR},",
          "ARTICLE_MODLE = #{articleModle,jdbcType=CHAR},",
          "ARTICLE_UPDTDT = #{articleUpdtdt,jdbcType=TIMESTAMP},",
          "ARTICLE_CONTENT = #{articleContent,jdbcType=LONGVARCHAR},",
          "ARTICLE_CONTENT_MARKDOWN = #{articleContentMarkdown,jdbcType=LONGVARCHAR},",
          "ARTICLE_HTML_NAME = #{articleHtmlName,jdbcType=LONGVARCHAR}, ",
          "ARTICLE_KEYWORDS = #{articleKeywords,jdbcType=VARCHAR}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Article record);

    @Select({
        "select",
        "ID, ARTICLE_NAME, ARTICLE_SHORT, ARTICLE_TP_ID, ARTICLE_CLICK, ARTICLE_UP, ARTICLE_MODLE, ARTICLE_UPDTDT, ",
        "ARTICLE_CONTENT,ARTICLE_CONTENT_MARKDOWN,ARTICLE_HTML_NAME,ARTICLE_KEYWORDS",
        "from article",
        "where ARTICLE_TP_ID = #{articleTpId,jdbcType=INTEGER}"
    })
	List<Article> seleByArticleTpId(Integer articleTpId);
    
    
    @Select({
        "select",
        "article_tp_nm ",
        "from article_type",
        "where ARTICLE_TP_ID = #{articleTpId,jdbcType=INTEGER}"
    })
	String selectArticleTpNmByTpId(Integer articleTpId);

    @Select({
            "select",
            " max(id) ",
            "from blog.article"
    })
    Integer selectMaxArticleId();


    @Select({
            "select a.TAG_ID from blog.tag a " ,
            "inner join blog.article_tag b " ,
            "on a.TAG_ID = b.tag_id " ,
            "inner join blog.article c " ,
            "on b.article_id=c.id  " ,
            "where 1=1 " ,
            "and c.id= #{articleId,jdbcType=INTEGER} "
    })
    List<Integer> selectArticleTagById(@Param(value = "articleId") Integer articleId);
}