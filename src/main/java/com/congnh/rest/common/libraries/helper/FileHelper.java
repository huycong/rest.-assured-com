package com.congnh.rest.common.libraries.helper;

import io.restassured.response.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class FileHelper {
    public static ResourceBundle rb = ResourceBundle.getBundle("settings");

    public static String file_content(String path) throws IOException {
        return Files.readString(Path.of(path), StandardCharsets.UTF_8);
    }


    public static Boolean download_file(String classname, String ext, Response res) throws IOException {
        String path = "src/test/java/" + classname.replace(".", "/") + "." + ext;
        byte[] byte_arr = res.asByteArray();
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(path);
            fos.write(byte_arr);
            fos.close();
            File downloaded = new File(path);
            System.out.println("Size: " + downloaded.length());
            return downloaded.length() > 100;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}

