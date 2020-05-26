//package com.zimu.study.common;
//
//import cc.newex.commons.support.model.ResponseResult;
//import cc.newex.commons.support.util.ResultUtils;
//import com.btb.sms.dto.SmsPayload;
//import com.btb.sms.service.YunPianInternational;
//import com.btb.sms.service.YunPianSmsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//
//@RestController
//@RequestMapping(value = "inner/api/sms/test")
//public class YupianController {
//
//    @Autowired
//    YunPianInternational service;
//
//    @Autowired
//    YunPianSmsService pianSmsService;
//
//    @PostMapping("/send")
//    @ResponseBody
//    public ResponseResult test_send() throws UnsupportedEncodingException {
//        SmsPayload smsPayload = new SmsPayload();
//        smsPayload.setAreacode("86");
//        smsPayload.setTemplate("EXT_FUTURE_WARNING");
//        smsPayload.setParamstr(URLEncoder.encode("{\"symbol\":\"BTC/USDT\"}", "UTF-8"));
////        smsPayload.setParamstr(URLEncoder.encode("{\"orderNo\":\"123456\",\"amount\":\"123456\",\"digitalSymbol\":\"123456\"}", "UTF-8"));
//        smsPayload.setPhone("18500041382");
//        smsPayload.setIp("white");
//
////        service.sendInternationalTest(smsPayload);
//        pianSmsService.sendDomesticTest(smsPayload);
//        return ResultUtils.success();
//    }
//}
