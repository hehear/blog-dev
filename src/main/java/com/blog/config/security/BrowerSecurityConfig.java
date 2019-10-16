package com.blog.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * spring security配置
 * 
 * @description
 * @author dengxiangying
 * @date 2018年9月20日
 */
@Configuration
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService customUserService;

	@Autowired
	SessionRegistry sessionRegistry;
	
	@Value("${editormd.images.upload}")
	private String UPLOADED_FOLDER;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().anyRequest().authenticated() // 任何请求,登录后可以访问
				.and().formLogin().loginPage("/login.do").failureUrl("/login?error").permitAll() // 登录页面用户任意访问
				.and()
				// 禁用csrf
				.csrf().disable();

		http.logout()
				// 触发注销操作的URL
				.logoutUrl("/user/logout")
				// 退出跳转
				.logoutSuccessUrl("/login.do")
				// 指定是否在注销时让HttpSession无效
				.invalidateHttpSession(true);

	}

	/**
	 * 密码加密
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	public SessionRegistry getSessionRegistry() {
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
		return sessionRegistry;
	}

	/**
	 * AuthenticationManager
	 */
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * 静态文件路径及允许的url
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// 解决静态资源被拦截的问题

		web.ignoring().antMatchers("/fonts/**");
		web.ignoring().antMatchers("/css/**");
		web.ignoring().antMatchers("/images/**");
		web.ignoring().antMatchers("/js/**");
		web.ignoring().antMatchers("/editormd/**");
		web.ignoring().antMatchers("/templates/**");
		web.ignoring().antMatchers("/user/loginIn");
		web.ignoring().antMatchers(UPLOADED_FOLDER+"/**");
	}

}