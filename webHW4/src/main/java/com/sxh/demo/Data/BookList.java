package com.sxh.demo.Data;

import lombok.Data;

import java.io.Serializable;
import java.util.Vector;

@Data
public class BookList implements Serializable {
    private Vector<ConInfo> bookinfo;
    public BookList() {
        bookinfo = new Vector<ConInfo>();
        bookinfo.add(new ConInfo("孙心华", "13863196385", "13863196385@163.com"
                , "北京市北京邮电大学", "2970652382", ""));
        bookinfo.add(new ConInfo("孙心华一", "18859824491", "1881310314@126.com"
                , "北京市北京邮电大学", "471874147", ""));
        bookinfo.add(new ConInfo("孙心华二", "13992810493", "7123891178@163.com"
                , "北京市北京邮电大学", "58798952", ""));
        bookinfo.add(new ConInfo("孙心华三", "13259109481", "813231597@qq.com"
                , "北京市北京邮电大学", "174582175", ""));
    }
}
