package com.xxl.codegenerator.core.util;

/**
 * string tool
 *
 * @author xuxueli 2018-05-02 20:43:25
 */
public class StringUtils {

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String upperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String lowerCase(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

}
