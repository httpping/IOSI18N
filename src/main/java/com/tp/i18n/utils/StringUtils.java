package com.tp.i18n.utils;

public class StringUtils {

    public static boolean isEmpty(String string){
        if (string == null || string.equals("")){
            return true;
        }
        return false;
    }
}
