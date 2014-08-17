package com.meteorite.core.config;

import org.apache.log4j.Logger;

import java.io.File;
import static com.meteorite.core.config.SystemConfig.*;

/**
 * 系统相关路径
 *
 * @author wei_jc
 * @since 1.0.0
 */
public class PathManager {
    private static final Logger log = Logger.getLogger(PathManager.class);
    private static final String CONFIG_FOLDER = "config";
    private static final String SYSTEM_FOLDER = "system";
    private static final String LOG_FOLDER = "log";
    private static final String TEMP_FOLDER = "temp";

    public static String USER_HOME = System.getProperty("user.home");
    public static String USER_DIR = System.getProperty("user.dir");
    public static File WEB_ROOT_DIR; // web项目根目录

    private static File homePath;
    private static File configPath;
    private static File systemPath;
    private static File logPath;
    private static File tempPath;

    /**
     * 获得系统主目录(.metaui)，桌面系统在user.home系统属性目录下，WEB系统在部署的web项目根目录下
     * 
     * @return 返回主目录(.metaui)
     */
    public static File getHomePath() {
        if (homePath != null) {
            return homePath;
        }
        String systemName = "." + SYSTEM_NAME;
        if (SYSTEM_TYPE == SystemType.WEB) {
            homePath = new File(WEB_ROOT_DIR, systemName);
        } else {
            homePath = new File(USER_HOME, systemName);
        }
        if (!homePath.exists()) {
            log.debug("创建主目录：" + homePath.getAbsolutePath());
            homePath.mkdir();
        }

        return homePath;
    }

    /**
     * 获得config目录（.metaui\config\）
     *
     * @return 返回config目录
     */
    public static File getConfigPath() {
        if (configPath != null) {
            return configPath;
        }

        configPath = new File(getHomePath(), CONFIG_FOLDER);
        if (!configPath.exists()) {
            log.debug("创建config目录：" + homePath.getAbsolutePath());
            configPath.mkdir();
        }
        return configPath;
    }

    /**
     * 获得system目录（.metaui\system\）
     *
     * @return 返回system目录
     */
    public static File getSystemPath() {
        if (systemPath != null) {
            return systemPath;
        }

        systemPath = new File(getHomePath(), SYSTEM_FOLDER);
        if (!systemPath.exists()) {
            log.debug("创建system目录：" + homePath.getAbsolutePath());
            systemPath.mkdir();
        }
        return systemPath;
    }

    /**
     * 获得log目录（.metaui\log\）
     *
     * @return 返回log目录
     */
    public static File getLogPath() {
        if (logPath != null) {
            return logPath;
        }

        logPath = new File(getHomePath(), LOG_FOLDER);
        if (!logPath.exists()) {
            log.debug("创建log目录：" + homePath.getAbsolutePath());
            logPath.mkdir();
        }
        return logPath;
    }

    /**
     * 获得temp目录（.metaui\temp\）
     *
     * @return 返回temp目录
     */
    public static File getTempPath() {
        if (tempPath != null) {
            return tempPath;
        }

        tempPath = new File(getHomePath(), TEMP_FOLDER);
        if (!tempPath.exists()) {
            log.debug("创建temp目录：" + homePath.getAbsolutePath());
            tempPath.mkdir();
        }
        return tempPath;
    }

    /**
     * 获得web项目下的某个目录
     *
     * @param path 相对路径
     * @return 返回File对象
     */
    public static File getWebPath(String path) {
        File file = new File(WEB_ROOT_DIR, path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
}