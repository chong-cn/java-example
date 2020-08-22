package com.java.example.utils;

import com.java.example.collection.OssClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

/**
 * 图片添加水印工具
 */
public class WaterMarkUtils {
    private static Logger logger = LoggerFactory.getLogger(WaterMarkUtils.class);

    /**
     * 添加水印
     *
     * @param srcImgPath       源图片路径
     * @param tarImgPath       保存的图片路径
     * @param markContentColor 水印颜色
     * @param waterMarkContent 水印内容
     * @param font             水印字体
     * @return:
     * @Date: 2020-04-03 15:05
     */
    public void addWaterMark(String srcImgPath, String tarImgPath, Color markContentColor, String waterMarkContent, Font font) {

        try {
            int fontSize = 300;

            // 读取原图片信息,得到文件
            File srcImgFile = new File(srcImgPath);
            // 文件转化为图片
            Image srcImg = ImageIO.read(srcImgFile);
            // 获取图片的宽
            int srcImgWidth = srcImg.getWidth(null);
            // 获取图片的高
            int srcImgHeight = srcImg.getHeight(null);
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            // 根据图片的背景设置水印颜色
            g.setColor(markContentColor);
            // 设置字体
            g.setFont(font);
            // 设置水印的坐标
            int watermarkLength = getWatermarkLength(waterMarkContent, g);
            while (watermarkLength > srcImgWidth) {
                fontSize = fontSize / 2;
                g.setFont(new Font("微软雅黑", Font.PLAIN, fontSize));
                watermarkLength = getWatermarkLength(waterMarkContent, g);
                logger.info("addWaterMark watermarkLength {} srcImgWidth {}", watermarkLength, srcImgWidth);
            }
            int x = (srcImgWidth - watermarkLength) / 2;
            int y = (srcImgHeight - fontSize) / 2;
            // 画出水印
            g.drawString(waterMarkContent, x, y);
            g.dispose();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufImg, "jpg", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            os.close();
            OssClient.uploadObject(is, is.available(), tarImgPath);

        } catch (Exception e) {
            logger.error("addWaterMark erro e = {}", e);
        }
    }

    /**
     * 通过流添加水印
     *
     * @param inputStream      图片流
     * @param waterMarkContent 水印文字
     * @param imgType          图片类型jpg、png等
     * @param imgType          字体大小，默认135
     * @return
     * @Date: 2020-04-03 15:05
     */
    public static InputStream addWaterMarkByStream(InputStream inputStream, String waterMarkContent, int fontSize, String imgType) {

        try {
            //参数校验
            if (inputStream == null || StringUtils.isBlank(imgType)) {
                return null;
            }
            if (StringUtils.isBlank(waterMarkContent)) {
                waterMarkContent = "图片来源：实名认证";
            }
            if (fontSize <= 0) {
                fontSize = 135;
            }

            Color markContentColor = new Color(255, 255, 255, 128);
            Font font = new Font("微软雅黑", Font.PLAIN, fontSize);

            // 读取原图片信息，文件转化为图片
            Image srcImg = ImageIO.read(inputStream);
            // 获取图片的宽
            int srcImgWidth = srcImg.getWidth(null);
            // 获取图片的高
            int srcImgHeight = srcImg.getHeight(null);
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            // 根据图片的背景设置水印颜色
            g.setColor(markContentColor);
            // 设置字体
            g.setFont(font);

            // 设置水印的坐标
            int watermarkLength = getWatermarkLength(waterMarkContent, g);
            for (int i = 0; i < 10 && watermarkLength > srcImgWidth; i++) {
                fontSize = fontSize / 2;
                g.setFont(new Font("微软雅黑", Font.PLAIN, fontSize));
                watermarkLength = getWatermarkLength(waterMarkContent, g);
                logger.info("addWaterMark watermarkLength {} srcImgWidth {} i {}", watermarkLength, srcImgWidth, i);
            }

            int x = (srcImgWidth - watermarkLength) / 2;
            int y = (srcImgHeight - fontSize) / 2;
            // 画出水印
            g.drawString(waterMarkContent, x, y);
            g.dispose();
            // 输出图片
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufImg, imgType, os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            os.close();
            logger.info("addWaterMarkByStream 添加水印完成 ");
            return is;

        } catch (Exception e) {
            logger.error("addWaterMarkByStream erro e = {}", e);
            return inputStream;
        }
    }

    public static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }

    /**
     * 克隆输入流
     *
     * @param input 输入
     * @return
     * @Date: 2020-04-03 15:05
     */
    public static ByteArrayOutputStream cloneInputStream(InputStream input) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static void main(String[] args) {
//        // 水印字体
//        Font font = new Font("微软雅黑", Font.PLAIN, 165);
//        // 源图片地址
//        String srcImgPath = "C:\\Users\\鲫鱼哥\\Pictures\\微信图片_20171018171107.png";
//        // 待存储的地址
//        String tarImgPath = "C:\\Users\\鲫鱼哥\\Pictures\\bbb.png";
//        // 水印内容
//        String waterMarkContent = "图片来源：www.at3.com";
//        // 水印图片色彩以及透明度
//        Color color = new Color(255, 255, 255, 128);
//        new WaterMarkUtils().addWaterMark(srcImgPath, tarImgPath, color, waterMarkContent, font);
//
//    }

}
