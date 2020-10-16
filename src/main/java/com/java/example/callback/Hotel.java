package com.java.example.callback;

/**
 * 酒店
 */
public class Hotel {

    /**
     * 酒店叫醒服务
     *
     * @param callBack 回调对象
     */
    public void wakeService(CallBack callBack) {
        System.out.println("主动调用：顾客预约了叫醒服务");
        try {
            // 模拟时间过了一晚上
            Thread.sleep(3000);
            System.out.println("中间处理：时间过去了一夜");
            callBack.beWakedUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
