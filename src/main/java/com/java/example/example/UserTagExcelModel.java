package com.java.example.example;

import lombok.Data;

@Data
public class UserTagExcelModel {

    private String tagTitle;

    private String tagKey;

    private Integer uid;

    public UserTagExcelModel(String tagTitle, String tagKey, Integer uid) {
        this.tagTitle = tagTitle;
        this.tagKey = tagKey;
        this.uid = uid;
    }
}
