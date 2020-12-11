package com.sxh.web5.Service;

import com.sxh.web5.Data.LoginInfor;

public class CheckLogin {

    public static boolean Check(LoginInfor infor) {
        if ("123".equals(infor.getUsername()) && "123".equals(infor.getPassword()))
            return true;
        else
            return false;
    }
}
