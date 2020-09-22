package com.java.example.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @Auther: L.C
 * @Date: 2020-03-21 23:11
 */
public class OssClient {
    protected static Logger logger = LoggerFactory.getLogger(OssClient.class);

    private static OSSClient _ossClient;
    private static ClientConfiguration conf;

    private static String accessKeyId = "";
    private static String accessKeySecret = "";
    private static String endpoint = "";
    private static String bucketName = "";

    static {
        conf = new ClientConfiguration();
        // 设置HTTP最大连接数为20
        conf.setMaxConnections(20);
        // 设置TCP连接超时为5000毫秒
        conf.setConnectionTimeout(5000);
        // 设置最大的重试次数为5
        conf.setMaxErrorRetry(5);
        // 设置Socket传输数据超时的时间为5000毫秒
        conf.setSocketTimeout(10000);
        // 代理服务器IP
        conf.setProxyHost("47.98.244.203");
        // 代理服务器端口
        conf.setProxyPort(3128);
    }

    public static OSSClient get_ossClient() {
        _ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
        return _ossClient;
    }

    /**
     * 上传到阿里云OSS空间 此方法只负责从服务器上传至阿里OSS
     *
     * @param originFullPath 文件在服务器的全路径：/home/work/temp/11111.jpg
     * @param fileName       上传到OSS后，访问路径：upload/11111.jpg
     * @throws FileNotFoundException
     */
    public static void uploadObject(String originFullPath, String fileName) throws FileNotFoundException {
        File file = new File(originFullPath);
        InputStream content = new FileInputStream(file);
        ObjectMetadata meta = new ObjectMetadata();
        // 必须设置ContentLength
        meta.setContentLength(file.length());
        // 上传Object.
        String bucketName = "";
        OssClient.get_ossClient().putObject(bucketName, fileName, content, meta);
    }

    /**
     * 上传文件到OSS
     *
     * @Author: L.C
     * @Date: 2020-03-22 01:17
     */
    public static void uploadObject(InputStream stream, long filelength, String path) throws FileNotFoundException {
        InputStream content = stream;
        ObjectMetadata meta = new ObjectMetadata();
        // 必须设置ContentLength
        meta.setContentLength(filelength);
        // 上传Object.
        PutObjectResult putObjectResult = OssClient.get_ossClient().putObject(bucketName, path, content, meta);
        logger.info("上传返回值：" + JSONObject.toJSONString(putObjectResult));
    }

    /**
     * 删除阿里云OSS的文件
     *
     * @param fileName 访问路径：upload/11111.jpg
     * @throws FileNotFoundException
     */
    public static void deleteObject(String fileName) throws FileNotFoundException {
        String bucketName = "";
        OssClient.get_ossClient().deleteObject(bucketName, fileName);
    }

}
