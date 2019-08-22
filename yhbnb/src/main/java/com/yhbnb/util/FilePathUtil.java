package com.yhbnb.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件目录工具类，获取相对路径的类
 *
 * @author zzy
 * @version 1.0
 * @since 2018/12/5 15:43
 */
@Slf4j
public abstract class FilePathUtil {
    private static Map<String, String> pathMap = new HashMap<>();

    /**
     * 获取指定目录绝对路径
     *
     * @param dir 目录
     * @return 绝对路径
     */
    private static String getPath(String dir) {
        String projectPath = FilePathUtil.pathMap.get("projectPath");
        if (projectPath == null) {
            String path = FilePathUtil.class.getResource("/").getPath();
            projectPath = new File(path).getParentFile().getParentFile().getParentFile().getAbsolutePath();
        }
        String path = projectPath + File.separator + dir;
        File file = new File(path);
        if (!file.exists()) {
            boolean b = file.mkdirs();
            if (!b)
                log.error("目录创建失败" + path);
        }
        return path;
    }

    /**
     * 获取文件上报路径
     *
     * @param chID     通道号
     * @param fileType 文件类型
     * @return 文件上报路径
     */
    public static String getFileUploadPath(String chID, String fileType) {

        return FilePathUtil.getFileuploadPath() + File.separator + "process" + File.separator + chID + File.separator + fileType;
    }

    /**
     * 获取数据库备份根路径
     *
     * @return 路径
     */
    public static String getDataBaseBackupsPath() {
        return FilePathUtil.getPath("dataBaseBackups");
    }

    /**
     * 获取数据归档根路径
     *
     * @return 路径
     */
    public static String getDataFilePath() {
        return FilePathUtil.getPath("dataFile");
    }

    /**
     * 获取下载文件根路径
     *
     * @return 路径
     */
    public static String getFiledownloadPath() {
        return FilePathUtil.getPath("filedownload");
    }

    /**
     * 获取上报文件根路径
     *
     * @return 路径
     */
    public static String getFileuploadPath() {
        return FilePathUtil.getPath("fileupload");
    }

    /**
     * 获取省调下发文件路径
     *
     * @return 路径
     */
    public static String getLowerHairFilePath() {
        return FilePathUtil.getPath("lowerHairFile");
    }

    /**
     * 获取日志文件根路径
     *
     * @return 路径
     */
    public static String getLogsPath() {
        return FilePathUtil.getPath("logs");
    }

    /**
     * 获取系统文件根路径
     * @return 路径
     */
    public static String getSystemFilePath() {
        return FilePathUtil.getPath("SystemFile");
    }

    /**
     * 获取临时文件目录
     *
     * @return 临时文件目录
     */
    public static String getTempPath() {
        return FilePathUtil.getPath("temp");
    }

    /**
     * 获取被删除文件目录
     *
     * @return 路径
     */
    public static String getDeleteFilePath() {
        return FilePathUtil.getPath("deleteFile");
    }

    /**
     * 设置工程路径，Tomcat程序包根路径
     *
     * @param path Tomcat程序包根路径
     */
    public static void setProjectPath(String path) {
        FilePathUtil.pathMap.put("projectPath", path);
    }


}
