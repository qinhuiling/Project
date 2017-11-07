package com.bwie.asus.projecttwo;

/**
 * Created by ASUS on 2017/11/7.
 */

public class MessageEvent {
    private String a;
    private String b;

    public MessageEvent(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
