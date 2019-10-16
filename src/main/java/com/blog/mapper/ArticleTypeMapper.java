package com.blog.mapper;

import com.blog.model.ArticleType;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface ArticleTypeMapper {
    @Delete({
        "delete from article_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into article_type (id, article_tp_id, ",
        "article_tp_nm, updt_tm)",
        "values (#{id,jdbcType=INTEGER}, #{articleTpId,jdbcType=INTEGER}, ",
        "#{articleTpNm,jdbcType=VARCHAR}, #{updtTm,jdbcType=TIMESTAMP})"
    })
    int insert(ArticleType record);

    @Select({
        "select",
        "id, article_tp_id, article_tp_nm, updt_tm",
        "from article_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    ArticleType selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "a.ARTICLE_TP_ID,a.ARTICLE_TP_NM,a.UPDT_TM,a.ID,COUNT(b.ARTICLE_TP_ID) AS ARTICLE_TP_NO ",
        " from blog.article_type a LEFT JOIN blog.article b",
        " ON a.ARTICLE_TP_ID=b.ARTICLE_TP_ID ",
        " GROUP BY a.ARTICLE_TP_ID,a.ARTICLE_TP_NM,a.UPDT_TM,a.ID ",
        " order by a.id,updt_tm desc "
    })
    List<ArticleType> selectAll();


    @Select({
            "<script>",
            "select",
            "ID, ARTICLE_TP_ID, ARTICLE_TP_NM, UPDT_TM ",
            "from  blog.ARTICLE_TYPE b",
            "where 1=1 ",
            " <if test='articleType.articleTpId !=null'>AND ARTICLE_TP_ID = #{articleType.articleTpId}</if>",
            "<choose> ",
            "<when test='articleType.articleTpNm'>",
            "    <bind name='articleTpNm' value=\"'%' + articleType.articleTpNm + '%'\"/>",
            "</when>",
            "<otherwise>",
            "    <bind name='articleTpNm' value=\"'%%'\"/> ",
            "</otherwise>",
            "</choose>",
            " <if test='articleType.articleTpNm !=null and !(\"\").equals(articleType.articleTpNm)'>AND ARTICLE_TP_NM like #{articleTpNm}</if>",
            " order by ID,UPDT_TM desc ",
            "</script>"
    })

    List<ArticleType> selectList(@Param(value = "articleType")ArticleType articleType);


    @Update({
        "update article_type",
        "set article_tp_id = #{articleTpId,jdbcType=INTEGER},",
          "article_tp_nm = #{articleTpNm,jdbcType=VARCHAR},",
          "updt_tm = #{updtTm,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ArticleType record);

}