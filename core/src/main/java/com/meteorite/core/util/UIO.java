package com.meteorite.core.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * IO工具类
 *
 * @author wei_jc
 * @version 1.0.0
 */
public class UIO {
    public static enum FROM {
        /**
         * ClassPath，类路径
         */
        CP,
        /**
         * FileSystem，文件系统
         */
        FS
    }

    /**
     * 获得InputStream
     *
     * @param path 文件路径
     * @param from 文件来源
     * @return 返回InputStream
     * @throws FileNotFoundException
     */
    public static InputStream getInputStream(String path, FROM from) throws FileNotFoundException {
        if(FROM.CP == from) {
            return UIO.class.getResourceAsStream(path);
        } else if (FROM.FS == from) {
            return new FileInputStream(path);
        }

        return null;
    }
}