package com.bizwell.entity;

/**
 * Created by charles on 2017/10/25.
 * 用于接收Request json数据
 */
public class RequestJson {

    private String sign;
    private String jsonData;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }
}
