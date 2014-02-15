package com.meteorite.core.util;

/**
 * @author wei_jc
 * @version 1.0.0
 */
public class UString {
    public static final String EMPTY = "";

    public static String isEmpty(String str, String defaultValue) {
        return isEmpty(str) ? defaultValue : str;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String substringBefore(String str, String separator) {
        if (isEmpty(str) || separator == null) {
            return str;
        }
        if (separator.length() == 0) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    public static String substringAfter(String str, String separator) {
        if (isEmpty(str)) {
            return str;
        }
        if (separator == null) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return EMPTY;
        }
        return str.substring(pos + separator.length());
    }

    /**
     * 将数据库的表名，转换为类名，例如sys_dbms_define,转换后的结果是DbmsDefine
     *
     * @param tableName 数据库表名
     * @return 返回类名
     */
    public static String tableNameToClassName(String tableName) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (String str : tableName.split("_")) {
            if (i++ == 0 && tableName.startsWith("sys_")) {
                continue;
            }

            result.append(firstCharToUpper(str.toLowerCase()));
        }

        return result.toString();
    }

    public static String columnNameToFieldName(String columnName) {
        StringBuilder result = new StringBuilder();
        for (String str : columnName.split("_")) {
            result.append(firstCharToUpper(str.toLowerCase()));
        }

        return result.toString();
    }

    /**
     * 首字母大写
     *
     * @param str 字符串
     * @return 返回首字母大写的字符串
     */
    public static String firstCharToUpper(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param str 字符串
     * @return 返回首字母小写的字符串
     */
    public static String firstCharToLower(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * 获取字符串的非Null值，如果为null，返回“”
     *
     * @param str 原始字符串
     * @param defaultStr 默认值
     * @return 如果str非Null，返回str，否则返回defaultStr，如果defaultStr为Null，则返回“”
     */
    public static String getNotNull(String str, String defaultStr) {
        return isEmpty(str) ? (isEmpty(defaultStr) ? "" : defaultStr) : str;
    }

    /**
     * 将字符串转化为boolean值
     *
     * @param str 字符串
     * @return 如果str等于"T"或者"true"，则返回true，否则返回false
     */
    public static boolean toBoolean(String str) {
        return !isEmpty(str) && ("T".equalsIgnoreCase(str) || "true".equalsIgnoreCase(str));
    }

    /**
     * 左边填充n个字符
     *
     * @param str 要填充的字符串
     * @param c 填充字符
     * @param n 填充个数
     * @return 返回填充后的字符串
     */
    public static String leftPadding(String str, char c, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(c);
        }
        return sb.toString() + str;
    }

    public static String substringByByte(String str, int i, int length) {
        return str.substring(i, i + length);
    }
}
