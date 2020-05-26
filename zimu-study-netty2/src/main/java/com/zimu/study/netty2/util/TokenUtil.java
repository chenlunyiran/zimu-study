package com.zimu.study.netty2.util;

import com.zimu.study.netty2.constant.RedisKeyConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author huangtao 2018/11/24 2:18 PM
 * @Description token
 */
@Slf4j
@Component
public class TokenUtil {

    @Autowired
    private StringRedisTemplate dataRedisTemplate;

    /**
     * 根据token获取userId
     * @param loginToken
     * @return
     */
    public Long getUserIdByLoginToken(String loginToken){
        String key = RedisKeyConstants.REDIS_KEY_LOGIN_TOKEN_PREFIX + loginToken;
        Object obj = dataRedisTemplate.opsForValue().get(key);
        if (obj != null){
            Long userId = Long.parseLong(String.valueOf(obj.toString()));
            // FOTA_APP_ACCT_LOGIN_TOKEN_
            String userKey = RedisKeyConstants.REDIS_KEY_LOGIN_TOKEN_USER_ID_LIST_PREFIX + userId;
            List<String> loginTokenList = dataRedisTemplate.opsForList().range(userKey, -5, -1);
            if (loginTokenList == null || !loginTokenList.contains(key)){
                // 不在list，删除该key
                dataRedisTemplate.delete(key);
                return null;
            }
            return userId;
        }
        return null;
    }
}
