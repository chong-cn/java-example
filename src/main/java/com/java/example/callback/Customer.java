package com.java.example.callback;

/**
 * 顾客
 */
public class Customer implements CallBack {

    // 顾客同时持有酒店类的对象，后面定义
    private Hotel hotel;

    public Customer(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void beWakedUp() {
        System.out.println("回调：顾客被叫醒了");
    }

    /**
     * 客户预约使用酒店的叫醒功能
     */
    public void bookWakeService() {
        hotel.wakeService(this);
    }

}
