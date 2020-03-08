package com.example.location.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    public static void createDirectory(String path) throws IOException {
        if (path != null && !path.equals("")) {
            System.out.println("ha path" + path);
            Files.createDirectories(Paths.get(path));
        }
    }

    public static void create(String path, String data) throws IOException {
        System.out.println("ha path :" + path);
        if (data != null && !data.equals("")) {
            Files.write(Paths.get(path), data.getBytes());
        }
    }

//    public static void generateFile(Class key, String content, String rootPath, String fileSuffix) throws IOException {
//        FileUtil.create(rootPath + "\\" + constructFileName(key, fileSuffix), content);
//
//    }

    public static void generateFile(String fileName, String content, String rootPath) throws IOException {
        FileUtil.create(rootPath + "\\" + fileName, content);

    }
//
//    public static String constructFilePath(Class key, String path) throws IOException {
//        return path + "\\" + StringFormatterUtil.upperCaseTheFirstLetter(key);
//    }
//
//    public static String constructFilePath(String filePath, String projectPath) throws IOException {
//        return projectPath + "\\" + StringFormatterUtil.lowerCaseTheFirstLetter(filePath);
//    }
//
//    public static String constructFilePath(Class key) throws IOException {
//        return constructFilePath(key, AppConfiguration.getProjectPath());
//    }
//
//    public static String constructFilePath(String filePath) throws IOException {
//        return constructFilePath(filePath, AppConfiguration.getProjectPath());
//    }
//
//    private static String constructFileName(Class key, String fileSuffix) {
//        return StringFormatterUtil.upperCaseTheFirstLetter(key) + fileSuffix;
//    }

    public static void copyFile(String sourcePath, String distPath) throws FileNotFoundException, IOException {
        OutputStream outStream = null;
        File afile = new File(sourcePath);
        File bfile = new File(distPath);
        outStream = new FileOutputStream(bfile);
        Files.copy(afile.toPath(), outStream);
    }

}
