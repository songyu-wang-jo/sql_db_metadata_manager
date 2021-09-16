package com.songyuwong.sqldbmetadatamanager.util;

public class StringUtils {

    public static boolean isBlank(String str) {
        int len;
        if (str != null && (len = str.length())>0){
            for (int i = 0; i < len; i++) {
                if (!Character.isWhitespace(str.charAt(i))) return false;
            }
        }
        return true;
    }

}
