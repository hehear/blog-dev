package com.blog.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件处理相关功能的类
 * @author dengxiangying
 * @date  2018年12月21日
 * @version V1.12.0
 */
public class FileUtil {

    /**
     * 获取指定目录下所有的文件的文件名，递归
     *
     * @param baseDir
     * @return
     */
    public static List<String> getAllFileNames(String baseDir) {
        File baseFile = new File(baseDir);

        if (!baseFile.exists()) {
            return null;
        }
        if (baseFile.isFile()) {
            return null;
        }
        List<String> fileNameList = new ArrayList<>();
        File[] srcFiles = baseFile.listFiles();
        for (File file : srcFiles) {
            if (file.isDirectory()) {
                fileNameList.addAll(getAllFileNames(file.getAbsolutePath()));
            } else {
                fileNameList.add(file.getAbsolutePath());
            }
        }
        return fileNameList;
    }

    /**
     * 文件的目录如果不存在，则创建所有目录
     * @param filePath 文件路径
     */
    public static void mkdirs(String filePath){
        if( null == filePath || filePath.trim().equals(""))
            return;
        File file = new File(filePath);
        if(file.exists()) {
            return;
        }
        file.getAbsoluteFile().getParentFile().mkdirs();
    }
    
   
}
