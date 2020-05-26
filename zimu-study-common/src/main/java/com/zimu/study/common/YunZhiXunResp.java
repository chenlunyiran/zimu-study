package com.zimu.study.common;

import lombok.Data;

@Data
public class YunZhiXunResp {
    public static final String SUCCESS_RETURN_STATUS = "Success";

    private String mobile;
    private String taskid;
    private String status;
    private String receivetime;
    private String errorcode;
    private String extno;
}
