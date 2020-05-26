package com.zimu.study.netty2.model;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2019-01-14.
 */
@Slf4j
public class LoopProvider {

    private static final DefaultLoop loop;

    static {
        if (KqueueLoop.hasKQueue()) {
            loop = new KqueueLoop();
        } else if (EpollLoop.hasEpoll()) {
            loop = new EpollLoop();
        } else {
            loop = new DefaultLoop() {
            };
        }
    }

    public static DefaultLoop getLoop() {
        return loop;
    }
}
