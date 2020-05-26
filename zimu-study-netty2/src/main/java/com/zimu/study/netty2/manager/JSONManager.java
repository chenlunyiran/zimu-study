package com.zimu.study.netty2.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zimu.study.netty2.annotation.Manager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

/**
 * Created on 2019-01-15.
 */
@Slf4j
@Manager
public class JSONManager {

    private ObjectMapper objectMapper;

    public <T> Optional<T> parseJSON(String json, Class<T> clazz) {
        try {
            return Optional.ofNullable(objectMapper.readValue(json, clazz));
        } catch (IOException e) {
            log.error("parse json error", e);
            return Optional.empty();
        }
    }

    public <T> String toJSONString(T src) {
        try {
            return objectMapper.writeValueAsString(src);
        } catch (JsonProcessingException e) {
            log.error("to JSON string error", e);
            return "";
        }
    }

    public <T> Optional<T> toPojo(TreeNode treeNode, Class<T> clazz) {
        try {
            return Optional.ofNullable(objectMapper.treeToValue(treeNode, clazz));
        } catch (JsonProcessingException e) {
            log.error("to pojo error", e);
            return Optional.empty();
        }
    }

    public ObjectNode newObjectNode() {
        return objectMapper.createObjectNode();
    }

    public ArrayNode newArrayNode() {
        return objectMapper.createArrayNode();
    }

    public ArrayNode listToArrayNode(Collection<?> list) {
        return objectMapper.valueToTree(list);
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
