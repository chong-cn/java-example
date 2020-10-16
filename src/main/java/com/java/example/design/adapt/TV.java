package com.java.example.design.adapt;

public class TV implements DualPin {

    @Override
    public void electrify(String live, String zero) {
        System.out.println("火线通电：" + live);
        System.out.println("零线通电：" + zero);
    }

}
