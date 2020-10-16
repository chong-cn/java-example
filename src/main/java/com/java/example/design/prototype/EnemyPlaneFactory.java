package com.java.example.design.prototype;

public class EnemyPlaneFactory {

    // 饿汉模式造一个敌机原型
    private static EnemyPlane protoType = new EnemyPlane(200);

    // 获取敌机克隆实例
    public static EnemyPlane getInstance(int x) throws CloneNotSupportedException {
        // 复制原型机
        EnemyPlane clone = protoType.clone();
        // 重新设置克隆机的x坐标
        clone.setX(x);
        return clone;
    }

}
