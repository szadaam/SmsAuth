package com.sztefanov.smsserver.network;

import java.io.Serializable;

public class Packet implements Serializable {

    private String tel, code;

    public Packet(String tel, String code) {
        this.tel = tel;
        this.code = code;
    }

    public String getTel() {
        return tel;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Packet{" + "tel=" + tel + ", code=" + code + '}';
    }

}
