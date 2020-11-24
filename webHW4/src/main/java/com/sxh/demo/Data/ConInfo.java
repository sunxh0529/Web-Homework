package com.sxh.demo.Data;

import lombok.Data;

import java.io.Serializable;

@Data
public class ConInfo implements Serializable {
    private String name;
    private String tel;
    private String email;
    private String address;
    private String qq;
    private String msg;

    public ConInfo(String _name, String _tel, String _email, String _address, String _qq, String _msg) {
        this.name = _name;
        this.tel = _tel;
        this.email = _email;
        this.qq = _qq;
        this.address = _address;
        this.msg = _msg;
    }
}
