package com.zimu.study.netty2.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author James Shen
 * @since 2018/10/18
 */
@Component
@Slf4j
public class SessionManager {

    private static final String USER_ID = "userId";

//    @Resource(name = "sessionRepository")
//    private SessionRepository sessionRepository;

    /**
     * 根据sessionId获取userId
     * @param sessionId
     * @return
     */
    public Long getUserId(String sessionId) {
//        Session session = sessionRepository.findById(sessionId);
//        if (session != null) {
//            return session.getAttribute(USER_ID);
//        }
        return null;
    }

}
