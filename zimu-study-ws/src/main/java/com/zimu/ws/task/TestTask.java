package com.zimu.ws.task;

import com.zimu.ws.model.ServerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

/**
 * @Classname TestTask
 * @Description TODO
 * @Date 2020/4/18 17:27
 * @Author jianhua.wang
 */
@Component
public class TestTask {

    @Autowired
    private SimpMessagingTemplate wsTemplate;

    @Scheduled(cron = "* * * * * ?")
    public void test() {
        System.out.println("TestTask start...");
        wsTemplate.convertAndSend("/topic/greetings", new ServerMessage("Hello, " + HtmlUtils.htmlEscape("task") + "!"));
    }
}
