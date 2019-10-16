package com.blog.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import com.blog.mapper.MenuMapper;
import com.blog.model.Menu;

/**
 * 项目启动时扫描所有菜单路径
 * @description
 * @author dengxiangying
 * @date 2018年9月20日
 */
@Service
public class MyInvocationSecurityMetadataSourceService  implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private MenuMapper cwMenuMapper ;

    private HashMap<String, Collection<ConfigAttribute>> map =null;

    /**
     * 加载菜单表中所有url
     */
    public void loadResourceDefine(){
        map = new HashMap<>();
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        //去除无url的父节点菜单
        List<Menu> cwMenus = cwMenuMapper.selectAllWithUrls();
        for(Menu cwMenu : cwMenus) {
            array = new ArrayList<>();
            cfg = new SecurityConfig(cwMenu.getMenuNm());
            //此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
            array.add(cfg);
            //用菜单的url 作为map的key，用ConfigAttribute的集合作为 value，
            map.put(cwMenu.getMenuLnk(), array);
        }

    }

    /**
     * 此方法是为了判定用户请求的url 是否在菜单表中，
     * 如果在菜单表中，则返回给 decide 方法，
     * 用来判定用户是否有此权限。如果不在菜单表中则放行。
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(map ==null) loadResourceDefine();
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            //有点菜单为父节点无url，跳过
            if(resUrl == null){
            	continue;
            }
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                return map.get(resUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
