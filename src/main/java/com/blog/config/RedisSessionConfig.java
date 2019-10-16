package com.blog.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionDeletedEvent;
import org.springframework.session.events.SessionDestroyedEvent;
import org.springframework.session.events.SessionExpiredEvent;
 
/**
 * redis-session配置
 * @description 可以通过注解配置session时效
 * @author dengxiangying
 * @date 2018年9月5日
 */
@Configuration
//maxInactiveIntervalInSeconds 默认是1800秒过期，这里测试修改为100秒
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=18000)
public class RedisSessionConfig{
	
	public static final Logger logger= LoggerFactory.getLogger(RedisSessionConfig.class);
	
	
    /**
     * Redis内session过期事件监听
     */
    @EventListener
    public void onSessionExpired(SessionExpiredEvent expiredEvent) {
        String sessionId = expiredEvent.getSessionId();
        logger.info("session："+sessionId+" 过期！ ");
    }


    /**
     * Redis内session删除事件监听
     */
    @EventListener
    public void onSessionDeleted(SessionDeletedEvent deletedEvent) {
        String sessionId = deletedEvent.getSessionId();
        logger.info("session："+sessionId+" 已删除！ ");
    
    }
    
    /**
     * Redis内session删除事件监听
     */
    @EventListener
    public void onSessionDestroyed(SessionDestroyedEvent destroyedEvent) {
        String sessionId = destroyedEvent.getSessionId();
        logger.info("session："+sessionId+" 已注销！ ");
    
    }

    /**
     * Redis内session保存事件监听
     */
    @EventListener
    public void onSessionCreated(SessionCreatedEvent createdEvent) {
        String sessionId = createdEvent.getSessionId();
        logger.info("session："+sessionId+" 已保存！  ");
    }
 
}