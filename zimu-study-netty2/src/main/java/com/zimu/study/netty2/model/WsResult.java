package com.zimu.study.netty2.model;

import com.alibaba.fastjson.JSON;
import com.zimu.study.netty2.enums.ErrorEnum;
import lombok.Data;

/**
 * @author James Shen
 * @since 2018/10/10
 */
@Data
public class WsResult<T> extends Result {

    /**
     * ws模块类型
     * @see com.fota.option.websocket.entity.enums.ModuleEnum
     */
    private int type;
    private int reqType;
    private int handleType;

    public static WsResult success(int code, int type) {
        WsResult wsResult = new WsResult();
        wsResult.setCode(code);
        wsResult.setType(type);
        return wsResult;
    }

    public static WsResult success(Object data, int type) {
        WsResult wsResult = new WsResult();
        wsResult.setType(type);
        wsResult.setReqType(type);
        wsResult.setHandleType(1);
        wsResult.setCode(DEFAULT_SUCCESS_CODE);
        wsResult.setData(data);
        return wsResult;
    }

    public static WsResult fail(ErrorEnum errorEnum, int type) {
        return fail(errorEnum.getCode(), errorEnum.getMsg(), type);
    }

    public static WsResult fail(ErrorEnum errorEnum, int type, Object data) {
        return fail(errorEnum.getCode(), errorEnum.getMsg(), type, data);
    }

    public static WsResult fail(int code, String msg, int type) {
        WsResult wsResult = new WsResult();
        wsResult.setMsg(msg);
        wsResult.setCode(code);
        wsResult.setType(type);
        return wsResult;
    }

    public static WsResult fail(int code, String msg, int type, Object data) {
        WsResult wsResult = new WsResult();
        wsResult.setMsg(msg);
        wsResult.setCode(code);
        wsResult.setType(type);
        wsResult.setData(data);
        return wsResult;
    }

    public String toJsonSting() {
        return JSON.toJSONString(this);
    }

    
}
