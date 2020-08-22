package com.java.example.utils;

import com.alibaba.fastjson.JSONObject;
import com.java.example.model.TextMessage;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;

/**
 * 钉钉工具
 */
public final class DingTalkUtil {

    /**
     * 发送预警钉钉信息
     *
     * @return:
     * @Date: 2020-08-22
     */
    public static void send(String message) {
        try {
            String webhook = "";
            String secret = "";
            if (StringUtils.isEmpty(webhook)) {
                return;
            }
            Long timestamp = System.currentTimeMillis();
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            String sign = URLEncoder.encode(new String(Base64Utils.encode(signData)), "UTF-8");

            webhook = webhook + "&timestamp=" + timestamp + "&sign=" + sign;

            HttpClientUtil.getInstance().doPostByApplicationJson(webhook, JSONObject.toJSONString(new TextMessage(message).toMap()));

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }
}
