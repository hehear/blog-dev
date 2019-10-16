package com.blog.mapper;

import com.blog.model.UserRole;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface UserRoleMapper {
    @Delete({
        "delete from user_role",
        "where ROLE_NO = #{roleNo,jdbcType=INTEGER}",
          "and USER_ID = #{userId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(@Param("roleNo") Integer roleNo, @Param("userId") String userId);

    @Insert({
        "insert into user_role (ROLE_NO, USER_ID, ",
        "UPDATE_TM, UPDATE_USR)",
        "values (#{roleNo,jdbcType=INTEGER}, #{userId,jdbcType=VARCHAR}, ",
        "#{updateTm,jdbcType=TIMESTAMP}, #{updateUsr,jdbcType=VARCHAR})"
    })
    int insert(UserRole record);

    @Select({
        "select",
        "ROLE_NO, USER_ID, UPDATE_TM, UPDATE_USR",
        "from user_role",
        "where ROLE_NO = #{roleNo,jdbcType=INTEGER}",
          "and USER_ID = #{userId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ROLE_NO", property="roleNo", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="UPDATE_TM", property="updateTm", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_USR", property="updateUsr", jdbcType=JdbcType.VARCHAR)
    })
    UserRole selectByPrimaryKey(@Param("roleNo") Integer roleNo, @Param("userId") String userId);

    @Select({
        "select",
        "ROLE_NO, USER_ID, UPDATE_TM, UPDATE_USR",
        "from user_role"
    })
    @Results({
        @Result(column="ROLE_NO", property="roleNo", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="UPDATE_TM", property="updateTm", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_USR", property="updateUsr", jdbcType=JdbcType.VARCHAR)
    })
    List<UserRole> selectAll();

    @Update({
        "update user_role",
        "set UPDATE_TM = #{updateTm,jdbcType=TIMESTAMP},",
          "UPDATE_USR = #{updateUsr,jdbcType=VARCHAR}",
        "where ROLE_NO = #{roleNo,jdbcType=INTEGER}",
          "and USER_ID = #{userId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(UserRole record);
}