package com.ruitukeji.zwbs.utils;

import java.util.regex.Pattern;

/**
 * Created by crobot on 2016/6/12.
 */

public  class MathUtil {
    public static String keepTwo(double originalNumber) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("##0.00");
        return df.format(originalNumber);
    }

    public static String keepZero(double originalNumber) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("##0");
        return df.format(originalNumber);
    }

    /**
     * 判断非负数的整数或者携带一位或者两位的小数
     *
     * @param obj
     * @return boolean
     * @throws
     * @function:
     * @author:
     * @since 1.0.0
     */
    public static boolean judgeTwoDecimal(Object obj) {
        boolean flag = false;
        try {
            if (obj != null) {
                String source = obj.toString();
                // 判断是否是整数或者是携带一位或者两位的小数
                Pattern pattern = Pattern.compile("^[+]?([0-9]+(.[0-9]{1,2})?)$");
                if (pattern.matcher(source).matches()) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
