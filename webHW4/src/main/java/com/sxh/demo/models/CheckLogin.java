package com.sxh.demo.models;

import com.sxh.demo.Data.LoginInfo;

public class CheckLogin {
    public static boolean Check(LoginInfo user) {
        if ("123".equals(user.getUsername()) && "123".equals(user.getPassword()))
            return true;
        else
            return false;
    }
}
