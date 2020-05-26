package com.zimu.study.netty2.util;

/**
 * @Author huangtao 2019/3/8 11:29 AM
 * @Description
 */
public class PageUtil {
    public static Integer getLimitStartNum(Integer pageNum, Integer pageSize) {
        Integer limitStartNum = 0;
        if (pageNum >= 1 && pageSize >= 1) {
            limitStartNum = (pageNum - 1) * pageSize;
        }
        return limitStartNum;
    }
}
