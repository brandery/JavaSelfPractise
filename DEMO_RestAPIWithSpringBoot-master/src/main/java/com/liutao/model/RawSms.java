package com.liutao.model;

import java.io.Serializable;

public class RawSms implements Serializable {
    public String phone ;
    public String userID;
    public String content;

    public RawSms(String para_phone, String para_userID, String para_content) {
        phone = para_phone;
        userID = para_userID;
        content = para_content;
    }

//    @Override
//    public String toString() {
//        return "{" +
//                "id='" + phone + '\'' +
//                ", userID='" + userID + '\'' +
//                ", content=" + content +
//                '}';
//    }
}
