package com.blog.config.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.blog.mapper.MenuMapper;
import com.blog.mapper.UserMapper;
import com.blog.model.Menu;


/**
 * 用于登录验证
 * 
 * @description
 * @author dengxiangying
 * @date 2018年9月20日
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("用户的用户名: {}", username);

		// 根据用户名，查找到对应的密码，与权限

		com.blog.model.User cwuser = userMapper.selectByPrimaryKey(username);
		if (cwuser != null) {
			List<Menu> cwMenus = menuMapper.findByUserId(cwuser.getUserId());

			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			for (Menu cwMenu : cwMenus) {
				if (cwMenu != null && cwMenu.getMenuNm() != null) {

					GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(cwMenu.getMenuNm());
					// 1：此处将权限信息添加到 GrantedAuthority
					// 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
					grantedAuthorities.add(grantedAuthority);
				}
			}

			User user = new User(cwuser.getUserId(), new BCryptPasswordEncoder().encode(cwuser.getUserPwd()),
					grantedAuthorities);

			return user;

		} else {
			throw new UsernameNotFoundException("用户: " + username + " 不存在!");
		}
	}
}