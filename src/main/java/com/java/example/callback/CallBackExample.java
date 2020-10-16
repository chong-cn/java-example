package com.java.example.callback;

/**
 * 测试回调类
 */
public class CallBackExample {

    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Customer customer = new Customer(hotel);
        customer.bookWakeService();
    }

}
