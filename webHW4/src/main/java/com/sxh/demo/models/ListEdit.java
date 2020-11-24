package com.sxh.demo.models;

import com.sxh.demo.Data.BookList;
import com.sxh.demo.Data.ConInfo;

import java.util.Vector;

public class ListEdit {
    public static boolean checkValid(BookList bookList, ConInfo conInfo) {
        boolean isValid = true;
        Vector<ConInfo> list = bookList.getBookinfo();
        for (int i = 0; i < list.size() && isValid; ++i) {
            if (list.elementAt(i).getName().equals(conInfo.getName()))
                isValid = false;
        }
        return isValid;
    }

    public static boolean editElem(BookList bookList, ConInfo conInfo) {
        int row = -1;
        Vector<ConInfo> list = bookList.getBookinfo();
        for (int i = 0; i < list.size() && -1 == row; ++i) {
            if (list.elementAt(i).getName().equals(conInfo.getName()))
                row = i;
        }

        if(row != -1) {
            list.set(row, conInfo);
            return true;
        }
        else
            return false;
    }

    public static void delElem(BookList bookList, int row) {
        bookList.getBookinfo().remove(row);
    }
}
