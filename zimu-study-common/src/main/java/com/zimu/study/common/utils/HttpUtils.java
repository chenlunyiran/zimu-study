package com.zimu.study.common.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    private static final String CHARSET = "UTF-8";

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private static final RequestConfig defaultRequestConfig  = RequestConfig.custom()
            .setConnectTimeout(10000)
            .setConnectionRequestTimeout(10000)
            .setSocketTimeout(10000)
            .build();

    public static String doGet(final String url, final Map<String, String> params) throws Exception {

        final StringBuilder query = new StringBuilder();

        if (params != null && params.size() > 0) {
            query.append("?");
            for (final Map.Entry<String, String> entry : params.entrySet()) {
                if (!StringUtils.isBlank(entry.getValue())) {
                    query.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                            .append("&");
                }
            }
            query.deleteCharAt(query.length() - 1);
        }

        final HttpGet httpGet = new HttpGet(url + query.toString());

        final CloseableHttpClient httpClient = HttpClients.createDefault();

        try {

            final HttpResponse response = httpClient.execute(httpGet);
            final HttpEntity entity = response.getEntity();

            return IOUtils.toString(entity.getContent(), "UTF-8");
        } catch (final Exception e) {
            logger.error("HttpUtils.doGet() error: url=" + url + ", params=" + params, e);
            throw e;
        } finally {
            httpClient.close();
        }

    }

    public static String doPost(final String url, final Map<String, String> params) throws Exception {

        final List<NameValuePair> list = new ArrayList<>();

        if (params != null) {
            for (final Map.Entry<String, String> entry : params.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        final HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));

        final CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig).build();;

        try {

            final HttpResponse response = httpClient.execute(httpPost);
            final HttpEntity entity = response.getEntity();

            return IOUtils.toString(entity.getContent(), "UTF-8");
        } catch (final Exception e) {
            logger.error("HttpUtils.doPost() error: url=" + url + ", params=" + params, e);
            throw e;
        } finally {
            httpClient.close();
        }

    }

    /**
     * http post请求
     *
     * @param url
     *            请求地址
     * @param params
     *            请求参数
     * @return
     */
    public static String post(String url, String params) {
        try {
            final CloseableHttpClient httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(defaultRequestConfig).build();;
            HttpPost httpPost = new HttpPost(url);
            StringEntity sEntity = new StringEntity(params, CHARSET);
            httpPost.setEntity(sEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity, CHARSET);
                }
            } finally {
                response.close();
                httpClient.close();
            }
        } catch (Exception e) {
            logger.error("get error",e);
            throw new RuntimeException(e);
        }
        return null;
    }
}
