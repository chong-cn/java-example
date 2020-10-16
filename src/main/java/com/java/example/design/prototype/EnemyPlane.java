package com.java.example.design.prototype;

public class EnemyPlane implements Cloneable{

    // 敌机横坐标
    private int x;
    // 敌机纵坐标
    private int y = 0;

    public EnemyPlane(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    // 让克隆后的实例重新修改x坐标
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void fly() {
        // 每调用一次，敌机飞行时纵坐标＋1
        y++;
    }

    // 重写克隆方法
    @Override
    public EnemyPlane clone() throws CloneNotSupportedException{
        return (EnemyPlane) super.clone();
    }


}
