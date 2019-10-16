package com.blog.config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

/**
 * url权限过滤
 * @description
 * 	初步url后转至此，判断用户是否有此权限
 *  若有权限则放行，无权限阻止访问
 * @author dengxiangying
 * @date 2018年9月20日
 */
@Service
public class MyAccessDecisionManager implements AccessDecisionManager {

	
	/**
	 * 判断是否有权限
	 * @description 
	 * authentication 是释CustomUserService中循环添加到 GrantedAuthority 对象中的权限信息集合.
	 * object 包含客户端发起的请求的requset信息，可转换为 HttpServletRequest request =((FilterInvocation) object).getHttpRequest();
	 * configAttributes 为MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法返回的结果，
	 * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide方法，用来判定用户是否有此权限。如果不在权限表中则放行。
	 */
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {

		if (null == configAttributes || configAttributes.size() <= 0) {
			return;
		}
		ConfigAttribute c;
		String needRole;
		for (Iterator<ConfigAttribute> iter = configAttributes.iterator(); iter.hasNext();) {
			c = iter.next();
			needRole = c.getAttribute();
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				// authentication 为在注释1中循环添加到 GrantedAuthority 对象中的权限信息集合
				if (needRole.trim().equals(ga.getAuthority())) {
					return;
				}
			}
		}
		throw new AccessDeniedException("no right");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
