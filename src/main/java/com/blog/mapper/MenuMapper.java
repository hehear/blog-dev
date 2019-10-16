package com.blog.mapper;

import com.blog.model.Menu;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

public interface MenuMapper {
    @Insert({
        "insert into menu (MENU_NO, MENU_NM, ",
        "MENU_PNO, MENU_LNK, ",
        "UPDATE_TM, UPDATE_USR)",
        "values (#{menuNo,jdbcType=INTEGER}, #{menuNm,jdbcType=VARCHAR}, ",
        "#{menuPno,jdbcType=INTEGER}, #{menuLnk,jdbcType=VARCHAR}, ",
        "#{updateTm,jdbcType=TIMESTAMP}, #{updateUsr,jdbcType=VARCHAR})"
    })
    int insert(Menu record);

    @Select({
        "select",
        "MENU_NO, MENU_NM, MENU_PNO, MENU_LNK, UPDATE_TM, UPDATE_USR",
        "from menu"
    })
    @Results({
        @Result(column="MENU_NO", property="menuNo", jdbcType=JdbcType.INTEGER),
        @Result(column="MENU_NM", property="menuNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="MENU_PNO", property="menuPno", jdbcType=JdbcType.INTEGER),
        @Result(column="MENU_LNK", property="menuLnk", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_TM", property="updateTm", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_USR", property="updateUsr", jdbcType=JdbcType.VARCHAR)
    })
    List<Menu> selectAll();
    
    @Select({
        "select",
        "MENU_NO, MENU_NM, MENU_PNO, MENU_LNK, UPDATE_TM, UPDATE_USR",
        "from MENU where MENU_LNK is not null "
    }) 
    List<Menu> selectAllWithUrls();
    
    @Select({
    	"<script>",
        "select ",
        "m.MENU_NO, m.MENU_NM, m.MENU_PNO, m.MENU_LNK, m.UPDATE_TM, m.UPDATE_USR",
        "from USER u",
        "LEFT JOIN USER_ROLE cur ON u.USER_ID= cur.USER_ID ",
        "LEFT JOIN ROLE r ON cur.ROLE_NO=r.ROLE_NO ",
        "LEFT JOIN ROLE_MENU crm ON crm.ROLE_NO=r.ROLE_NO ",
        "LEFT JOIN MENU m ON m.MENU_NO =crm.MENU_NO ",
        "where 1=1 ", 
        " and u.USER_ID = #{userId} ",
        "</script>"
    })
	List<Menu> findByUserId(@Param(value = "userId")String userId);
}