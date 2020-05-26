package com.zimu.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author junmingyang
 */
@EnableScheduling
@SpringBootApplication
public class DemoWebSocketServer {

    public static void main(String[] args) {
        SpringApplication.run(DemoWebSocketServer.class, args);
    }
}
