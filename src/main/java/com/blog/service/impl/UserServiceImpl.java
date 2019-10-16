package com.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.blog.mapper.UserMapper;
import com.blog.service.IUserService;
/**
 * service 实现类
 * @description
 * @author dengxiangying
 * @date 2019年1月8日
 */
@Service("userService")
@CacheConfig(cacheNames="user")
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper ;

	
	

}
