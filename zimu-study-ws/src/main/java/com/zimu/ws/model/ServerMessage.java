package com.zimu.ws.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author junmingyang
 */
@Data
@AllArgsConstructor
public class ServerMessage {

    private String content;

    public ServerMessage() {
    }

    @Override
    public String toString() {
        return content;
    }

}
