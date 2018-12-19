package com.prt.iwarehouse.utils;

import android.text.TextUtils;

import com.prt.baselibrary.log.KLoggerToast;

/**
 * @author 请叫我张懂
 * @Date 2018/4/1 14:15
 * @Description
 */

public class CodeTypeCheckUtil {
    private static final int REEL_ID_LENGTH = 14;
    private static final int REEL_ID_LENGTH_MAX=17;

    public static boolean isBomId(String code) {
        boolean result = false;
        if (!TextUtils.isEmpty(code) &&
                code.length() <= 20) {
            result = true;
        }
        return result;
    }

    public static boolean isReelId(String code) {
        boolean result = false;
        if (!TextUtils.isEmpty(code) && !code.contains("!") &&
                code.length() >= REEL_ID_LENGTH &&code.length()<=REEL_ID_LENGTH_MAX) {
            result = true;
        }
        return result;
    }

    public static boolean isContainerCode(String code) {
        boolean result = false;
        if (!TextUtils.isEmpty(code) &&
                code.length() == 5) {

            result = true;
        }

        return result;
    }
    public static boolean isSubCode(String code){
        if(code.matches("[0-9A-Za-z]+(.)+[0-9A-Za-z]+(.)+[0-9A-Za-z]"))
            return true;
        else
            return false;
    }
    public static boolean isSeatCode(String code) {
        boolean result = false;
        if (!TextUtils.isEmpty(code) &&
                code.length() == 8) {

            result = true;
        }

        return result;
    }

    public static boolean isEquipId(String code) {
        boolean result = false;
        if (!TextUtils.isEmpty(code)&&code.matches("^[0-9A-Fa-f]+$")) {
            result = true;
        }

        return result;
    }

    public static boolean isNumeric(String code) {
        for (int i = code.length(); --i >= 0; ) {
            if (!Character.isDigit(code.charAt(i))) {
                return false;
            }
        }
        return true;
    }


}
