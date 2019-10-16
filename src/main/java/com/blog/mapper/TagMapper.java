package com.blog.mapper;

import com.blog.model.ArticleTagRltn;
import com.blog.model.ArticleType;
import com.blog.model.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TagMapper {


    @Delete({
        "delete from blog.tag",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Delete({
            "delete from blog.article_tag",
            "where article_id = #{id,jdbcType=INTEGER}"
    })
    int deleteArticleTagRltnByArticleId(Integer id);


    @Insert({
        "insert into blog.tag (id, tag_id, ",
        "tag_nm, updt_tm)",
        "values (#{id,jdbcType=INTEGER}, #{tagId,jdbcType=INTEGER}, ",
        "#{tagNm,jdbcType=VARCHAR}, #{updtTm,jdbcType=TIMESTAMP})"
    })
    int insert(Tag record);

    @Insert({
            "insert into blog.article_tag (id, article_id, ",
            "tag_id, updt_tm)",
            "values (#{id,jdbcType=INTEGER}, #{articleId,jdbcType=INTEGER}, ",
            "#{tagId,jdbcType=INTEGER}, #{updtTm,jdbcType=TIMESTAMP})"
    })
    int insertArticleTagRltn(ArticleTagRltn record);

    @Select({
        "select",
        "id, TAG_ID, TAG_NM, updt_tm",
        "from blog.tag",
        "where id = #{id,jdbcType=INTEGER}"
    })
    ArticleType selectByPrimaryKey(Integer id);

    @Select({
        " select ",
        " a.TAG_ID,a.TAG_NM,a.UPDT_TM,a.ID ",
        " from blog.tag a",
        " order by a.id,a.updt_tm desc "
    })
    List<Tag> selectAll();

    @Update({
        "update blog.tag",
        "set TAG_ID = #{tagId,jdbcType=INTEGER},",
          "TAG_NM = #{tagNm,jdbcType=VARCHAR},",
          "updt_tm = #{updtTm,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Tag record);


    @Select({
            "<script>",
            "select",
            "ID, TAG_ID, TAG_NM, UPDT_TM ",
            "from  blog.tag b",
            "where 1=1 ",
            " <if test='tag.tagId !=null'>AND TAG_ID = #{tag.tagId}</if>",
            "<choose> ",
            "<when test='tag.tagNm'>",
            "    <bind name='tagNm' value=\"'%' + tag.tagNm + '%'\"/>",
            "</when>",
            "<otherwise>",
            "    <bind name='tagNm' value=\"'%%'\"/> ",
            "</otherwise>",
            "</choose>",
            " <if test='tag.tagNm !=null and !(\"\").equals(tag.tagNm)'>AND TAG_NM like #{tagNm}</if>",
            " order by ID,UPDT_TM desc ",
            "</script>"
    })

    List<Tag> selectList(@Param(value = "tag")Tag tag);
}