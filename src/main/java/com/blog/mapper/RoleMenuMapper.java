package com.blog.mapper;

import com.blog.model.RoleMenu;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface RoleMenuMapper {
    @Delete({
        "delete from role_menu",
        "where ROLE_NO = #{roleNo,jdbcType=INTEGER}",
          "and MENU_NO = #{menuNo,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(@Param("roleNo") Integer roleNo, @Param("menuNo") Integer menuNo);

    @Insert({
        "insert into role_menu (ROLE_NO, MENU_NO, ",
        "UPDATE_TM, UPDATE_USR)",
        "values (#{roleNo,jdbcType=INTEGER}, #{menuNo,jdbcType=INTEGER}, ",
        "#{updateTm,jdbcType=TIMESTAMP}, #{updateUsr,jdbcType=VARCHAR})"
    })
    int insert(RoleMenu record);

    @Select({
        "select",
        "ROLE_NO, MENU_NO, UPDATE_TM, UPDATE_USR",
        "from role_menu",
        "where ROLE_NO = #{roleNo,jdbcType=INTEGER}",
          "and MENU_NO = #{menuNo,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ROLE_NO", property="roleNo", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="MENU_NO", property="menuNo", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="UPDATE_TM", property="updateTm", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_USR", property="updateUsr", jdbcType=JdbcType.VARCHAR)
    })
    RoleMenu selectByPrimaryKey(@Param("roleNo") Integer roleNo, @Param("menuNo") Integer menuNo);

    @Select({
        "select",
        "ROLE_NO, MENU_NO, UPDATE_TM, UPDATE_USR",
        "from role_menu"
    })
    @Results({
        @Result(column="ROLE_NO", property="roleNo", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="MENU_NO", property="menuNo", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="UPDATE_TM", property="updateTm", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_USR", property="updateUsr", jdbcType=JdbcType.VARCHAR)
    })
    List<RoleMenu> selectAll();

    @Update({
        "update role_menu",
        "set UPDATE_TM = #{updateTm,jdbcType=TIMESTAMP},",
          "UPDATE_USR = #{updateUsr,jdbcType=VARCHAR}",
        "where ROLE_NO = #{roleNo,jdbcType=INTEGER}",
          "and MENU_NO = #{menuNo,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RoleMenu record);
}