package com.java.example.design.adapt;

public interface TriplePin {

    // 参数分别为火线live，零线zero，地线earth
    public void electrify(String live, String zero, String earth);

}
