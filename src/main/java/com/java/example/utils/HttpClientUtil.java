package com.java.example.utils;


import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final int DEFAULT_TIMEOUT = 30000;
    private static HttpClientUtil ins;
    private HttpClient client;
    private int TIME_OUT = DEFAULT_TIMEOUT;
    // 最大不要超过1000
    private static int MAX_CONN_TOTAL = 200;
    // 实际的单个连接池大小，
    private static int MAX_CONN_PER_ROUTE = 100;
    // 代理HOST
    private static String PROXY_HOST = "";
    // 代理端口
    private static String PROXY_PORT = "";

    public HttpClientUtil() {
        if (client == null) {
            client = HttpClients.createDefault();
        }
    }

    public static HttpClientUtil getInstance() {
        if (ins == null) {
            synchronized (HttpClientUtil.class) {
                if (ins == null) {
                    ins = new HttpClientUtil();
                }
            }
        }
        return ins;
    }

    public String doGetWithJsonResult(String uri) {
        String json = null;
        logger.debug("========= Call [{}] Start ==========", uri);
        HttpResponse response = null;
        try {
            HttpGet request = new HttpGet(uri);
            RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(TIME_OUT)
                    .setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT).build();
            request.setConfig(config);
            response = client.execute(request);
            logger.debug("Response status code: {}", response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                logger.debug("Payload : {}", json);
            }
        } catch (Exception e) {
            logger.error("HttpClient has exception! message: {}", e.getMessage(), e);
            return null;
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (IOException e) {
                logger.error("HttpClient has exception! message: {}", e.getMessage(), e);
            }
        }
        logger.debug("========= Call [{}] End ==========", uri);
        return json;
    }

    public String doGetWithJsonResult(String uri, int timeout) {
        setTimeout(TIME_OUT);
        return doGetWithJsonResult(uri);
    }

    public String doGetCustom(String uri) {
        String json = null;
        logger.debug("========= Call [{}] Start ==========", uri);
        HttpResponse response = null;
        try {
            HttpGet request = new HttpGet(uri);
            RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(TIME_OUT)
                    .setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT).build();

            request.setConfig(config);
            client = HttpClients.custom().setDefaultRequestConfig(config)
                    .setMaxConnTotal(MAX_CONN_TOTAL)
                    .setMaxConnPerRoute(MAX_CONN_PER_ROUTE).build();
            response = client.execute(request);
            logger.debug("Response status code: {}", response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                logger.debug("Payload : {}", json);
            }
        } catch (Exception e) {
            logger.error("HttpClient has exception! message: {}", e.getMessage(), e);
            return null;
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (IOException e) {
                logger.error("HttpClient has exception! message: {}", e.getMessage(), e);
            }
        }
        logger.debug("========= Call [{}] End ==========", uri);
        return json;
    }


    public static File getFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            return file;
        }
    }

    /**
     * 代理使用
     *
     * @return:
     * @Date: 2020-08-22
     */
    public String doPostWithJsonResult(String uri, Map<String, Object> paramMap) {
        String json = null;
        logger.debug("========= Call [{}] Start ==========", uri);
        HttpResponse response = null;
        try {
            HttpPost request = new HttpPost(uri);
            RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(TIME_OUT)
                    .setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT).build();
            request.setConfig(config);
            List<NameValuePair> params = new ArrayList<NameValuePair>(0);
            if (paramMap != null && !paramMap.isEmpty()) {
                for (String key : paramMap.keySet()) {
                    params.add(new BasicNameValuePair(key, paramMap.get(key).toString()));
                }
                request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            }

            /** 代理 */
            if (uri.startsWith("https://")) {
                logger.info("https-proxy");
                client = HttpClients.custom()
                        .setDefaultRequestConfig(config)
                        .setProxy(new HttpHost(PROXY_HOST, Integer.parseInt(PROXY_PORT)))
                        .setMaxConnTotal(MAX_CONN_TOTAL)
                        .setMaxConnPerRoute(MAX_CONN_PER_ROUTE).build();
            } else {
                logger.info("http-proxy");
                client = HttpClients.custom()
                        .setDefaultRequestConfig(config)
                        .setProxy(new HttpHost(PROXY_HOST, Integer.parseInt(PROXY_PORT)))
                        .setMaxConnTotal(MAX_CONN_TOTAL)
                        .setMaxConnPerRoute(MAX_CONN_PER_ROUTE).build();
            }

            response = client.execute(request);
            logger.debug("Response status code: {}", response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                logger.debug("Payload : {}", json);
            }
            request.releaseConnection();
        } catch (Exception e) {
            logger.error("HttpClient has exception! message: {}", e.getMessage(), e);
            return null;
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (IOException e) {
                logger.error("HttpClient has exception! message: {}", e.getMessage(), e);
            }
        }
        logger.debug("========= Call [{}] End ==========", uri);
        return json;
    }


    public String doPostImageUpload(String uri, Map<String, Object> paramMap) {
        String json = null;
        logger.debug("========= Call [{}] Start ==========", uri);
        HttpResponse response = null;
        try {
            HttpPost request = new HttpPost(uri);
            RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(TIME_OUT)
                    .setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT).build();
            request.setConfig(config);
            request.addHeader("Content-type", "multipart/form-data");
            List<NameValuePair> params = new ArrayList<NameValuePair>(0);
            if (paramMap != null && !paramMap.isEmpty()) {
                for (String key : paramMap.keySet()) {
                    params.add(new BasicNameValuePair(key, paramMap.get(key).toString()));
                }
                request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            }
            response = client.execute(request);
            logger.debug("Response status code: {}", response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                logger.debug("Payload : {}", json);
            }
            request.releaseConnection();
        } catch (Exception e) {
            logger.error("HttpClient has exception! message: {}", e.getMessage(), e);
            return null;
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (IOException e) {
                logger.error("HttpClient has exception! message: {}", e.getMessage(), e);
            }
        }
        logger.debug("========= Call [{}] End ==========", uri);
        return json;
    }


    public String doPostWithJsonResult(String uri, String jsonParameters) {
        logger.debug("========= Call [{}] Start ==========", uri);
        logger.debug("========= Call [{}] Start ==========", jsonParameters);
        HttpPost request = new HttpPost(uri);
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(TIME_OUT)
                .setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT).build();
        request.setConfig(config);
        request.setEntity(new StringEntity(jsonParameters, ContentType.APPLICATION_JSON));
        HttpResponse response = null;
        String responseStr = null;
        try {
            response = client.execute(request);
            logger.debug("Response status code: {}", response.getStatusLine().getStatusCode());
            responseStr = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            logger.debug("Payload : {}", responseStr);
        } catch (Exception e) {
            logger.error(e.getMessage(), new IllegalStateException(e));
        }
        logger.debug("========= Call [{}] End ==========", uri);
        return responseStr;

    }

    public String doPostWithJsonResultThrowsException(String uri, String jsonParameters) throws Exception {
        logger.debug("========= Call [{}] Start ==========", uri);
        logger.debug("========= Call [{}] Start ==========", jsonParameters);
        HttpPost request = new HttpPost(uri);
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(TIME_OUT)
                .setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT).build();
        request.setConfig(config);
        request.setEntity(new StringEntity(jsonParameters, ContentType.APPLICATION_JSON));
        HttpResponse response = null;
        String responseStr = null;
        try {
            response = client.execute(request);
            logger.debug("Response status code: {}", response.getStatusLine().getStatusCode());
            responseStr = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            logger.debug("Payload : {}", responseStr);
        } catch (Exception e) {
            logger.error(e.getMessage(), new IllegalStateException(e));
            throw new Exception();
        }
        logger.debug("========= Call [{}] End ==========", uri);
        return responseStr;

    }


    public String doPost(String url, String jsonStr) {
        logger.debug("========= Call [{}] Start ==========", url);
        URL u = null;
        HttpURLConnection con = null;

        try {
            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            //application/x-www-form-urlencoded
            //con.setRequestProperty("content-type", "*/*");
            con.setConnectTimeout(TIME_OUT);
            con.setReadTimeout(TIME_OUT);
            //con.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            if (jsonStr != null && !"".equals(jsonStr)) {
                OutputStreamWriter osw = new OutputStreamWriter(
                        con.getOutputStream(), "UTF-8");
                logger.debug("即将发送参数:{}", jsonStr);
                osw.write(jsonStr);
                osw.flush();
                osw.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), new IllegalStateException(e));
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        // 读取返回内容
        StringBuffer buffer = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                    con.getInputStream(), "UTF-8"));
            String temp = "";
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), new IllegalStateException(e));
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String result = buffer.toString();
        logger.debug("Payload: {}", result);
        logger.debug("========= Call [{}] End ==========", url);
        return result;
    }

    public String doPostByApplicationJson(String url, String jsonStr) {
        logger.debug("========= Call [{}] Start ==========", url);
        URL u = null;
        HttpURLConnection con = null;

        try {
            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            //application/x-www-form-urlencoded
            //con.setRequestProperty("content-type", "*/*");
            con.setConnectTimeout(TIME_OUT);
            con.setReadTimeout(TIME_OUT);
            con.setRequestProperty("content-type", "application/json");
            if (jsonStr != null && !"".equals(jsonStr)) {
                OutputStreamWriter osw = new OutputStreamWriter(
                        con.getOutputStream(), "UTF-8");
                logger.debug("即将发送参数:{}", jsonStr);
                osw.write(jsonStr);
                osw.flush();
                osw.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), new IllegalStateException(e));
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        // 读取返回内容
        StringBuffer buffer = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                    con.getInputStream(), "UTF-8"));
            String temp = "";
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), new IllegalStateException(e));
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String result = buffer.toString();
        logger.debug("Payload: {}", result);
        logger.debug("========= Call [{}] End ==========", url);
        return result;
    }

    public void setTimeout(int timeout) {
        this.TIME_OUT = timeout;
    }

    public String doPost(String url, String jsonStr, int timeout) {
        setTimeout(TIME_OUT);
        return doPost(url, jsonStr);
    }

    public String doGetCustom(String url, int timeout) {
        setTimeout(TIME_OUT);
        return doGetCustom(url);
    }


}
